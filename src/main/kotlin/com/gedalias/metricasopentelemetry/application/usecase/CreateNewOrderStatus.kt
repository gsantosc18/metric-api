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
            throw OrderNotFoundException("OrderId ${command.orderId} not found")
        }
        if(order?.status != PENDING) {
            throw OrderStatusNotPendingException("Order status is not pending: status=${order?.status}")
        }
        return orderStatusService.save(command)
    }
}