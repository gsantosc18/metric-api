package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.Status.PENDING
import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Example
import java.math.BigDecimal.TEN

class OrderServiceTest {
    private val orderRepository: OrderRepository = mockk(relaxed = true)
    private val orderService = OrderService(orderRepository)

    fun mockkOrderEntity(): OrderEntity {
        val user = UserEntity(orders = emptyList(), id = null, email = null, name = null, birthday = null, createdAt = null)
        return OrderEntity(id = null, user = user, updatedAt = null, createdAt = null, status = PENDING, value = TEN, products = null)
    }

    @Test
    fun `Save new order`() {
        val orderEntity = mockkOrderEntity()

        every { orderRepository.save(any()) } returns orderEntity

        orderService.save(Order.create(user = User.create(), value = TEN, status = PENDING))

        verify { orderRepository.save(any()) }
    }

    @Test
    fun `Find orders by filter`() {
        val orderEntity = mockkOrderEntity()

        every { orderRepository.findAll(any<Example<OrderEntity>>()) } returns listOf(orderEntity)

        val order = Order.create()
        val findBy = orderService.findBy(order)

        verify { orderRepository.findAll(any<Example<OrderEntity>>()) }

        assertEquals(1, findBy.size)
    }

    @Test
    fun `Find orders without filter`() {
        every { orderRepository.findAll() } returns listOf(mockkOrderEntity())

        orderService.findBy(null)

        verify { orderRepository.findAll() }
    }
}