package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test

class OrderServiceImplTest {
    private val orderRepository: OrderRepository = mockk(relaxed = true)
    private val orderService = OrderServiceImpl(orderRepository)

    @Test
    fun `Should save new order`() {

    }
}