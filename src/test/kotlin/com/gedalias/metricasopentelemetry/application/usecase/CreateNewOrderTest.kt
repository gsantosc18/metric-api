package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.domain.exception.UserNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime.now

class CreateNewOrderTest {
    private val orderService: OrderService = mockk(relaxed = true)
    private val userService: UserService = mockk(relaxed = true)
    private val createNewOrder = CreateNewOrder(orderService, userService)

    @Test
    fun `Should create a new order`() {
        val order = Order.create(user = User.create(id = "111"))

        every { orderService.save(order) } returns Order.create(createdAt = now())
        every { userService.findById(any()) } returns order.user

        createNewOrder.execute(order)

        verify { orderService.save(order) }
    }

    @Test
    fun `Should not create order if throw exception`() {
        val order = Order.create(user = User.create(id = "111"))
        every { userService.findById(any()) } returns order.user
        every { orderService.save(any()) } throws IllegalArgumentException()
        assertThrows<java.lang.IllegalArgumentException> { createNewOrder.execute(order) }
    }

    @Test
    fun `Should not create order if user not exist`() {
        val userId = "111"
        val order = Order.create(
            user = User.create(id = userId)
        )

        every { userService.findById(userId) } returns null

        assertThrows<UserNotFoundException> { createNewOrder.execute(order) }

        verify { userService.findById(userId) }
        verify(exactly = 0) { orderService.save(any()) }
    }
}