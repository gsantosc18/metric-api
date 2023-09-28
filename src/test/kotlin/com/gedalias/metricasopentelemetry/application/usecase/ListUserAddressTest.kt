package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.AddressService
import com.gedalias.metricasopentelemetry.domain.Address
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListUserAddressTest {
    private val addressService: AddressService = mockk(relaxed = true)
    private val listUserAddress = ListUserAddress(addressService)

    @Test
    fun `Should return list of user address`() {
        val addresses = listOf(
            Address.create(userId = "1"), Address.create(userId = "1"), Address.create(userId = "1")
        )

        every { addressService.findByUserId("1") } returns addresses

        val addressList = listUserAddress.execute("1")

        verify { addressService.findByUserId("1") }
        Assertions.assertEquals(3, addressList.size)
    }
}