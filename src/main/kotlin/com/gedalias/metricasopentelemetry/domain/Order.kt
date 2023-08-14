package com.gedalias.metricasopentelemetry.domain

data class Order(
    val user: User,
    val products: List<Product>
)
