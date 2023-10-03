package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.domain.exception.OrderStatusNotPendingException
import com.gedalias.metricasopentelemetry.domain.Status.PENDING
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import java.util.Objects.isNull

class CreateNewOrderStatus(
    private val orderService: OrderService,
    private val orderStatusService: OrderStatusService
): Command<OrderStatus, OrderStatus> {
    override fun execute(command: OrderStatus): OrderStatus {
        val order = orderService.findById(requireNotNull(command.orderId))
        if (isNull(order)) {
            throw OrderNotFoundException("Order ${command.orderId} not found")
        }
        if(order?.status != PENDING) {
            throw OrderStatusNotPendingException("Order ${command.orderId} is not pending")
        }
        return orderStatusService.save(command)
    }
}