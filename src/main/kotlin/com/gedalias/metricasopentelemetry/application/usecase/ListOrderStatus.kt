package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.domain.OrderStatus

class ListOrderStatus(
    private val orderStatusService: OrderStatusService
): Command<String, List<OrderStatus>> {
    override fun execute(command: String): List<OrderStatus> =
        orderStatusService.findByOrderId(command)
}