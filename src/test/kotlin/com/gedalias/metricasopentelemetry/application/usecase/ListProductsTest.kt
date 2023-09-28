package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.ProductService
import com.gedalias.metricasopentelemetry.domain.Product
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListProductsTest {
    private val productService: ProductService = mockk(relaxed = true)
    private val listProducts = ListProducts(productService)

    @Test
    fun `Should return list of products`() {
        val productsRecord = listOf(
            Product.create(), Product.create(), Product.create()
        )

        every { productService.findBy(any()) } returns productsRecord

        val productList = listProducts.execute(null)

        verify { productService.findBy(any()) }
        assertEquals(3, productList.size)
    }
}