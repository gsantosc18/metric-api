package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.domain.exception.OrderStatusNotPendingException
import com.gedalias.metricasopentelemetry.domain.Status.CANCELLED
import com.gedalias.metricasopentelemetry.domain.Status.PENDING
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class CreateNewOrderStatusTest {
    private val orderService: OrderService = mockk(relaxed = true)
    private val orderStatusService: OrderStatusService = mockk(relaxed = true)

    private val createNewOrderStatus = CreateNewOrderStatus(orderService, orderStatusService)

    @Test
    fun `Should save new order status`() {
        val orderId = "1"
        val orderStatus = OrderStatus.create(orderId = orderId)

        every { orderStatusService.save(orderStatus) } returns OrderStatus.create(
            orderId = "1",
            createdAt = LocalDateTime.now(),
        )

        every { orderService.findById(orderId) } returns Order.create(status = PENDING)

        val orderStatusRecord = createNewOrderStatus.execute(orderStatus)

        verify { orderStatusService.save(any()) }

        assertTrue(orderStatusRecord.orderId?.isNotEmpty() == true)
        assertTrue(orderStatusRecord.createdAt != null)
    }

    @Test
    fun `Should not save new status if not exist order`() {
        val orderId = "1"

        every { orderService.findById(orderId) } returns null

        assertThrows<OrderNotFoundException> { createNewOrderStatus.execute(OrderStatus.create(orderId = orderId)) }

        verify { orderService.findById(orderId) }
    }

    @Test
    fun `Should not save new status if order status is not pending`() {
        val orderId = "1"
        val orderRecord = Order.create(id = orderId, status = CANCELLED)

        every { orderService.findById(orderId) } returns orderRecord

        assertThrows<OrderStatusNotPendingException> { createNewOrderStatus.execute(OrderStatus.create(orderId = orderId)) }

        verify { orderService.findById(orderId) }
    }
}