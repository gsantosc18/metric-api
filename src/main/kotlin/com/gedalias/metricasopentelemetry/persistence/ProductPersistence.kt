package com.gedalias.metricasopentelemetry.persistence

import com.gedalias.metricasopentelemetry.domain.Product

interface ProductPersistence {

    fun save(product: Product): Product

    fun all(): List<Product>

}