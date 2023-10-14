package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDateTime

data class OrderStatus(
    val id: String?,
    val orderId: String?,
    val description: String?,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun create(
            id: String? = null,
            orderId: String? = null,
            description: String? = null,
            createdAt: LocalDateTime? = null
        ) = OrderStatus(
            id = id,
            orderId = orderId,
            description = description,
            createdAt = createdAt
        )
    }
}
