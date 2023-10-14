package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.ProductService
import com.gedalias.metricasopentelemetry.domain.Product

class ListProducts(
    private val productService: ProductService
): Command<Product?, List<Product>> {
    override fun execute(command: Product?): List<Product> =
        productService.findBy(command)
}