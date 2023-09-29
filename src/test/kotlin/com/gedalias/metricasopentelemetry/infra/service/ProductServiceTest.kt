package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Example

class ProductServiceTest {
    private val productRepository: ProductRepository = mockk(relaxed = true)
    private val productService = ProductService(productRepository)

    @Test
    fun `Save new product`() {
        val productEntity = ProductEntity(id = null, title = null, description = null, value = null)

        every { productRepository.save(any()) } returns productEntity

        productService.save(Product.create())

        verify { productRepository.save(any()) }
    }

    @Test
    fun `Find products by filter`() {
        val productEntity = ProductEntity(id = null, title = null, description = null, value = null)

        every { productRepository.findAll(any<Example<ProductEntity>>()) } returns listOf(productEntity)

        val findBy = productService.findBy(Product.create())

        verify { productRepository.findAll(any<Example<ProductEntity>>()) }

        assertEquals(1, findBy.size)
    }

    @Test
    fun `Find products without filter`() {
        val productEntity = ProductEntity(id = null, title = null, description = null, value = null)

        every { productRepository.findAll() } returns listOf(productEntity)

        val findBy = productService.findBy(null)

        verify { productRepository.findAll() }

        assertEquals(1, findBy.size)
    }
}