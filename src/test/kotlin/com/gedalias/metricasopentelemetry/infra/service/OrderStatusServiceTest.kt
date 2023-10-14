package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.OrderStatus
import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderStatusEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderStatusRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime.now
import java.util.*

class OrderStatusServiceTest {
    private val orderStatusRepository: OrderStatusRepository = mockk(relaxed = true)
    private val orderService: OrderService = mockk(relaxed = true)
    private val orderStatusService = OrderStatusService(orderStatusRepository, orderService)

    @Test
    fun `Create new order status record`() {
        val orderId = "111"
        val orderStatus = OrderStatus.create(orderId = orderId)

        val orderStatusEntity = OrderStatusEntity(id = UUID.randomUUID().toString(), description = "On road", orderId = orderId, createdAt = now())

        every { orderService.findById(orderId) } returns Order.create()
        every { orderStatusRepository.save(any<OrderStatusEntity>()) } returns orderStatusEntity

        orderStatusService.save(orderStatus)

        verify { orderStatusRepository.save(any<OrderStatusEntity>()) }
    }

    @Test
    fun `Not create order status if order not found`() {
        val orderId = "111"
        val orderStatus = OrderStatus.create(orderId = orderId)

        every { orderService.findById(orderId) } returns null

        assertThrows<OrderNotFoundException> { orderStatusService.save(orderStatus) }

        verify(exactly = 0) { orderStatusRepository.save(any<OrderStatusEntity>()) }
    }

    @Test
    fun `Find order status by orderId`() {
        val orderId = "1111"
        val orderStatusEntity = OrderStatusEntity(id = "1", orderId = orderId, description = null, createdAt = null)

        every { orderStatusRepository.findByOrderId(orderId) } returns listOf(orderStatusEntity)

        val findByOrderId = orderStatusService.findByOrderId(orderId)

        verify { orderStatusRepository.findByOrderId(orderId) }
        assertEquals(1, findByOrderId.size)
    }

    @Test
    fun `Return empty list if not found orderId`() {
        val orderId = "1111"

        every { orderStatusRepository.findByOrderId(orderId) } returns emptyList()

        val findByOrderId = orderStatusService.findByOrderId(orderId)

        verify { orderStatusRepository.findByOrderId(orderId) }
        assertEquals(0, findByOrderId.size)
    }
}