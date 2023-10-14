package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException

class UpdateOrder(
    private val orderService: OrderService
) : Command<Order, Order> {

    override fun execute(command: Order): Order {
        checkIfOrderExist(command.id)
        return orderService.save(command)
    }

    private fun checkIfOrderExist(orderId: String?) {
        orderId?.let(orderService::findById) ?: throw OrderNotFoundException("Order $orderId not found")
    }
}