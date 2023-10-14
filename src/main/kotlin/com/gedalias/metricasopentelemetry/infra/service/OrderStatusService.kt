package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderStatusRepository
import com.gedalias.metricasopentelemetry.infra.service.mapper.toDomain
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import org.springframework.stereotype.Service

@Service
class OrderStatusService(
    private val orderStatusRepository: OrderStatusRepository,
    private val orderService: OrderService
): OrderStatusService {
    override fun save(orderStatus: OrderStatus): OrderStatus {
        checkIfExistOrder(orderStatus.orderId)
        return orderStatus.toEntity().let(orderStatusRepository::save).toDomain()
    }

    override fun findByOrderId(orderId: String): List<OrderStatus> =
        orderStatusRepository.findByOrderId(orderId).map{ it.toDomain() }

    private fun checkIfExistOrder(orderId: String?) {
        orderId?.let(orderService::findById) ?: throw OrderNotFoundException("Order $orderId not found")
    }
}