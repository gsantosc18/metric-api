package com.gedalias.metricasopentelemetry.service

import com.gedalias.metricasopentelemetry.domain.dto.CreateProductDTO
import com.gedalias.metricasopentelemetry.domain.dto.ProductDTO

interface ProductService {

    fun create(createProductDTO: CreateProductDTO): ProductDTO

    fun all(): List<ProductDTO>

}