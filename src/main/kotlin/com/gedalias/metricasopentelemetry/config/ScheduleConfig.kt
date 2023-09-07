package com.gedalias.metricasopentelemetry.config

import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.domain.OrderStatus.*
import com.gedalias.metricasopentelemetry.domain.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateOrderDTO
import com.gedalias.metricasopentelemetry.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.random.Random

@EnableJpaAuditing
@EnableScheduling
@Configuration
class ScheduleConfig(
        private val orderService: OrderService
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    private val mapStatus = listOf(CANCELLED, PAID)

    @Scheduled(cron = "0 */1 * * * *")
    fun removePending() {
        logger.info("Iniciando a cron de cancelamento")
        orderService.all()
                .filter {
                    it.valid()
                }
                .also {
                    logger.info("Serão canceladas {} pedido(s) : {}", it.size, it)
                }
                .map {
                    UpdateOrderDTO(
                            id = it.id,
                            user = it.user?.id,
                            products = it.products?.map { p -> p.id!! },
                            status = randomStatus(),
                            value = it.value
                    )
                }
                .forEach{
                    orderService.update(it.id!!, it)
                    logger.info("Atualização do pedido : {}", it)
                }
    }

    private fun randomStatus(): OrderStatus {
        val position = Random.nextInt(1,10)
        return mapStatus[if(position <= 5) 0 else 1]
    }

    @Scheduled(cron = "0 */1 * * * *")
    fun generateRandomOrder() {
        logger.info("Iniciada a geração das compras aleatórias")
        val dto = CreateOrderDTO(
                user = "ef8e6fc1-ba82-4105-8076-28750e6a8af6",
                products = listOf("70534e14-37fd-4831-9c49-c4f96af9e9da"),
                value = BigDecimal.valueOf(150)
        )
        val times = Random.nextInt(1, 20)
        for (i in times downTo 0 step 1) {
            orderService.save(dto)
        }
        logger.info("Criadas {} compras aleatórias", times)
    }

    private fun OrderDTO.valid() =
            status == PENDING && (isBeforeNow() || noHasCreatedAt())

    private fun OrderDTO.isBeforeNow(): Boolean =
        createdAt?.plusMinutes(2)?.isBefore(LocalDateTime.now()) == true

    private fun OrderDTO.noHasCreatedAt(): Boolean = createdAt == null

}