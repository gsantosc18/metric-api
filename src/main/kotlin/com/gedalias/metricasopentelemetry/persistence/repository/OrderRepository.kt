package com.gedalias.metricasopentelemetry.persistence.repository

import com.gedalias.metricasopentelemetry.persistence.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, String>
