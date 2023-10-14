package com.gedalias.metricasopentelemetry.infra.database.repository

import com.gedalias.metricasopentelemetry.infra.database.entity.OrderStatusEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderStatusRepository: JpaRepository<OrderStatusEntity, String> {

    fun findByOrderId(orderId: String): List<OrderStatusEntity>

}