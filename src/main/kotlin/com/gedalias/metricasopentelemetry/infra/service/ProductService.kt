package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.ProductService
import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.infra.database.repository.ProductRepository
import com.gedalias.metricasopentelemetry.infra.service.mapper.toDomain
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
): ProductService {
    override fun save(product: Product): Product =
        product.toEntity()
            .let(productRepository::save)
            .toDomain()

    override fun findBy(product: Product?): List<Product> =
        when {
            product != null -> productRepository.findAll(Example.of(product.toEntity()))
            else -> productRepository.findAll()
        }.map { it.toDomain() }
}