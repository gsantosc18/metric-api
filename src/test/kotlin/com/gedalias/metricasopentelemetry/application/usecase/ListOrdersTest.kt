package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListOrdersTest {
    private val orderService: OrderService = mockk(relaxed = true)
    private val listOrders = ListOrders(orderService)

    @Test
    fun `Should return list of orders`() {
        val records = listOf(
            Order.create(), Order.create(), Order.create()
        )

        every { orderService.findBy(any()) } returns records

        val foundOrders = listOrders.execute(null)

        verify { orderService.findBy(any()) }
        assertEquals(3, foundOrders.size)
    }
}