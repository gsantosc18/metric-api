package com.gedalias.metricasopentelemetry.infra.http.dto

import java.time.LocalDateTime

data class CreateNewOrderStatusDTO(
    val orderId: String?,
    val description: String?
)

data class OrderStatusDTO(
    val id: String?,
    val orderId: String?,
    val description: String?,
    val createdAt: LocalDateTime?
)