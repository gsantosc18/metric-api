package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDateTime

data class OrderStatus(
    val orderId: String?,
    val description: String?,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun create(
            orderId: String? = null,
            description: String? = null,
            createdAt: LocalDateTime? = null
        ) = OrderStatus(
            orderId = orderId,
            description = description,
            createdAt = createdAt
        )
    }
}
