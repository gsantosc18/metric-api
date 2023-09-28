package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.ProductService
import com.gedalias.metricasopentelemetry.domain.Product
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreateNewProductTest {
    private val productService: ProductService = mockk(relaxed = true)
    private val createNewProduct = CreateNewProduct(productService)

    @Test
    fun `Should save new product`() {
        val product = Product.create()

        every { productService.save(product) } returns Product.create(id = "1")

        createNewProduct.execute(product)

        verify { productService.save(product) }
    }
}