package com.gedalias.metricasopentelemetry.application.service

import com.gedalias.metricasopentelemetry.domain.OrderStatus

interface OrderStatusService {

    fun save(orderStatus: OrderStatus): OrderStatus

    fun findByOrderId(orderId: String): List<OrderStatus>
}