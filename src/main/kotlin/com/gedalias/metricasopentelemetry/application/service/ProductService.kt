package com.gedalias.metricasopentelemetry.application.service

import com.gedalias.metricasopentelemetry.domain.Product

interface ProductService {

    fun save(product: Product): Product

    fun findBy(product: Product?): List<Product>
}