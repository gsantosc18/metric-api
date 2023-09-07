package com.gedalias.metricasopentelemetry.persistence.mapper

import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.persistence.entity.ProductEntity

fun Product.toEntity() = ProductEntity(
        id = id,
        title = title,
        description = description,
        value = value
)

fun ProductEntity.toDomain() = Product(
        id = id,
        title = title,
        description = description,
        value = value
)