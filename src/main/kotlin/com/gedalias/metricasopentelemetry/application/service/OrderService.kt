package com.gedalias.metricasopentelemetry.application.service

import com.gedalias.metricasopentelemetry.domain.Order

interface OrderService {

    fun findBy(order: Order?): List<Order>

    fun save(order: Order): Order

    fun findById(id: String): Order?

}