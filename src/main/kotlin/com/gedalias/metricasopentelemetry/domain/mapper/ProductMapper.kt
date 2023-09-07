package com.gedalias.metricasopentelemetry.domain.mapper

import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.domain.dto.CreateProductDTO
import com.gedalias.metricasopentelemetry.domain.dto.ProductDTO

fun CreateProductDTO.toDomain() = Product(
        id = null,
        title = title,
        description = description,
        value = value
)

fun Product.toDTO() = ProductDTO(
        id = id,
        title = title,
        description = description,
        value = value
)

fun ProductDTO.toDomain() = Product(
        id = id,
        title = title,
        description = description,
        value = value
)