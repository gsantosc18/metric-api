package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.OrderCounter
import com.gedalias.metricasopentelemetry.infra.service.mapper.toDomain
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import org.springframework.data.domain.Example
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
): OrderService {
    override fun findBy(order: Order?): List<Order> =
        when {
            order != null -> orderRepository.findAll(Example.of(order.toEntity()))
            else -> orderRepository.findAll()
        }.map { it.toDomain() }

    @OrderCounter
    override fun save(order: Order): Order =
        order.toEntity().let(orderRepository::save).toDomain()

    override fun findById(id: String): Order? =
        orderRepository.findByIdOrNull(id)?.toDomain()
}