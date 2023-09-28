package com.gedalias.metricasopentelemetry.infra.service.mapper

import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity

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