package com.gedalias.metricasopentelemetry.domain

import java.math.BigDecimal

data class Product(
    val title: String,
    val description: String,
    val value: BigDecimal
)
