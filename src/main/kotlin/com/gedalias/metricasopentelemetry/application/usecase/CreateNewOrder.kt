package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order


class CreateNewOrder(
    private val orderService: OrderService
): Command<Order, Order> {
    override fun execute(command: Order): Order =
        orderService.save(command)
}