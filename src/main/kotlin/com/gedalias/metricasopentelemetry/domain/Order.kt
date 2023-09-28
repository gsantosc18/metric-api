package com.gedalias.metricasopentelemetry.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val id: String?,
    val user: User?,
    val products: List<Product>?,
    val value: BigDecimal?,
    val status: Status?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun create(
            id: String? = null,
            user: User? = null,
            products: List<Product>? = null,
            value: BigDecimal? = null,
            status: Status? = null,
            createdAt: LocalDateTime? = null,
            updatedAt: LocalDateTime? = null
        ): Order = Order(
            id = id,
            user = user,
            products = products,
            value = value,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
