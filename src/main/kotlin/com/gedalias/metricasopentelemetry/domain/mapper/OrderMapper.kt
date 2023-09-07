package com.gedalias.metricasopentelemetry.domain.mapper

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.OrderStatus.PENDING
import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.domain.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateOrderDTO

fun OrderDTO.toDomain() = Order(
        id = id,
        user = user?.toDomain(),
        products = products?.map { it.toDomain() },
        value = value,
        status = status,
        createdAt = createdAt,
        updatedAt = createdAt
)

fun Order.toDTO() = OrderDTO(
        id = id,
        user = user?.toDTO(),
        products = products?.map { it.toDTO() },
        value = value,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
)

fun CreateOrderDTO.toDomain() = Order(
        id = null,
        user = User(user),
        products = products.map { Product(it) },
        value = value,
        status = PENDING,
        createdAt = null,
        updatedAt = null
)

fun UpdateOrderDTO.toDomain() = Order(
        id = id,
        user = User(user),
        products = products?.map { Product(it) },
        value = value,
        status = status,
        createdAt = null,
        updatedAt = null
)