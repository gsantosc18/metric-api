package com.gedalias.metricasopentelemetry.infra.service.mapper

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity

fun OrderEntity.toDomain() = Order(
        id = id,
        user = user.toDomain(),
        products = products?.map { it.toDomain() },
        value = value,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
)

fun Order.toEntity() = OrderEntity(
        id = null,
        user = requireNotNull(user).toEntity(),
        products = products?.map { it.toEntity() },
        value = requireNotNull(value),
        status = requireNotNull(status)
)

fun Order.update(id: String) =
        this.toEntity().copy(id = id)
