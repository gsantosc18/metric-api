package com.gedalias.metricasopentelemetry.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
        val id: String?,
        val user: User?,
        val products: List<Product>?,
        val value: BigDecimal?,
        val status: OrderStatus?,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?
)
