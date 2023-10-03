package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.Status.CANCELLED
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UpdateOrderTest {
    private val orderService: OrderService = mockk(relaxed = true)
    private val updateOrder = UpdateOrder(orderService)

    @Test
    fun `Update order`() {
        val order = Order.create(id = "123", status = CANCELLED)

        every { orderService.save(order) } returns order
        every { orderService.findById(order.id!!) } returns mockk()

        updateOrder.execute(order)

        verify { orderService.save(order) }
    }

    @Test
    fun `Not update order if not found`() {
        val order = Order.create(id = "123", status = CANCELLED)

        every { orderService.findById(order.id!!) } returns null

        assertThrows<OrderNotFoundException> { updateOrder.execute(order) }

        verify(exactly = 0) { orderService.save(order) }
    }
}