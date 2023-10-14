package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.domain.OrderStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListOrderStatusTest {
    private val orderStatusService: OrderStatusService = mockk(relaxed = true)
    private val listOrderStatus = ListOrderStatus(orderStatusService)

    @Test
    fun `Should return list of order status`() {
        val orderStatusRecords = listOf(
            OrderStatus.create(), OrderStatus.create(), OrderStatus.create()
        )

        every { orderStatusService.findByOrderId(any()) } returns orderStatusRecords

        val orderStatusList = listOrderStatus.execute("1")

        verify { orderStatusService.findByOrderId(any()) }
        assertEquals(3, orderStatusList.size)
    }
}