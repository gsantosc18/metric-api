package com.gedalias.metricasopentelemetry.infra.service.mapper

import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderStatusEntity

fun OrderStatus.toEntity() = OrderStatusEntity(
    id = null,
    orderId = orderId,
    description = description,
    createdAt = null
)

fun OrderStatusEntity.toDomain() = OrderStatus(
    id = id,
    orderId = orderId,
    description = description,
    createdAt = createdAt
)