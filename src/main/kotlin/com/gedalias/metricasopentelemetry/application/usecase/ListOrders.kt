package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order

class ListOrders(
    private val orderService: OrderService
): Command<Order?, List<Order>> {
    override fun execute(command: Order?): List<Order> =
        orderService.findBy(command)
}