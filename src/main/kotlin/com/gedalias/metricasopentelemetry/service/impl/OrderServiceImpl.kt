package com.gedalias.metricasopentelemetry.service.impl

import com.gedalias.metricasopentelemetry.domain.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateOrderDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDomain
import com.gedalias.metricasopentelemetry.metrics.OrderMetrics
import com.gedalias.metricasopentelemetry.persistence.OrderPersistence
import com.gedalias.metricasopentelemetry.service.OrderService
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
        private val orderPersistence: OrderPersistence,
        private val orderMetrics: OrderMetrics
): OrderService {

    override fun all(): List<OrderDTO> =
            orderPersistence.all().map { it.toDTO() }

    override fun save(createOrderDTO: CreateOrderDTO): OrderDTO =
            createOrderDTO.toDomain()
                    .let(orderPersistence::save)
                    .toDTO()
                    .also(orderMetrics::incrementOrder)

    override fun findById(id: String): OrderDTO? =
            orderPersistence.findById(id)?.toDTO()

    override fun update(id: String, updateOrderDTO: UpdateOrderDTO): OrderDTO? =
            orderPersistence.update(id, updateOrderDTO.toDomain())
                    ?.toDTO()
                    ?.also(orderMetrics::incrementOrder)

}