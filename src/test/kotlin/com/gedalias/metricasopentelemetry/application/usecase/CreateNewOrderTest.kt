package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime.now

class CreateNewOrderTest {
    private val orderService: OrderService = mockk(relaxed = true)
    private val createNewOrder = CreateNewOrder(orderService)

    @Test
    fun `Should create a new order`() {
        val order = Order.create()

        every { orderService.save(order) } returns Order.create(createdAt = now())

        createNewOrder.execute(order)

        verify { orderService.save(order) }
    }

    @Test
    fun `Should not create order if throw exception`() {
        every { orderService.save(any()) } throws IllegalArgumentException()
        assertThrows<java.lang.IllegalArgumentException> { createNewOrder.execute(mockk()) }
    }
}