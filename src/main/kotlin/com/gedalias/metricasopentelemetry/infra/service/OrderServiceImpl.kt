package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
): OrderService {
    override fun findBy(order: Order?): List<Order> {
        return emptyList()
    }

    override fun save(order: Order): Order {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Order? {
        TODO("Not yet implemented")
    }

    override fun update(id: String, order: Order): Order? {
        TODO("Not yet implemented")
    }

}