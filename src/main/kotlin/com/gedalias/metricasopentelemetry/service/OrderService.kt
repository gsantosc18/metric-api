package com.gedalias.metricasopentelemetry.service

import com.gedalias.metricasopentelemetry.domain.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateOrderDTO

interface OrderService {

    fun all(): List<OrderDTO>

    fun save(createOrderDTO: CreateOrderDTO): OrderDTO

    fun findById(id: String): OrderDTO?

    fun update(id: String, updateOrderDTO: UpdateOrderDTO): OrderDTO?

}