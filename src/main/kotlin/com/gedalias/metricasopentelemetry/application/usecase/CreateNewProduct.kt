package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.ProductService
import com.gedalias.metricasopentelemetry.domain.Product

class CreateNewProduct(
    private val productService: ProductService
): Command<Product, Product> {
    override fun execute(command: Product): Product =
        productService.save(command)
}