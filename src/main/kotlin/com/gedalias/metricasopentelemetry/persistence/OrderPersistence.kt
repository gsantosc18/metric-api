package com.gedalias.metricasopentelemetry.persistence

import com.gedalias.metricasopentelemetry.domain.Order

interface OrderPersistence {

    fun all(): List<Order>

    fun save(order: Order): Order

    fun findById(id: String): Order?

    fun update(id: String, order: Order): Order?

}