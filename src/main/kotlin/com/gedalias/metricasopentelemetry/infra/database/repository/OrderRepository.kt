package com.gedalias.metricasopentelemetry.infra.database.repository

import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, String>
