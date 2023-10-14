package com.gedalias.metricasopentelemetry.infra.http.mapper

import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateNewOrderStatusDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.OrderStatusDTO

fun CreateNewOrderStatusDTO.toDomain() = OrderStatus(
    id = null,
    orderId = orderId,
    description = description,
    createdAt = null
)

fun OrderStatus.toDTO() = OrderStatusDTO(
    id = id,
    orderId = orderId,
    description = description,
    createdAt = createdAt
)