package com.gedalias.metricasopentelemetry.persistence.impl

import com.gedalias.metricasopentelemetry.domain.Product
import com.gedalias.metricasopentelemetry.persistence.ProductPersistence
import com.gedalias.metricasopentelemetry.persistence.mapper.toDomain
import com.gedalias.metricasopentelemetry.persistence.mapper.toEntity
import com.gedalias.metricasopentelemetry.persistence.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductPersistenceImpl(
        private val repository: ProductRepository
): ProductPersistence {

    override fun save(product: Product): Product =
        product.toEntity()
                .let(repository::save)
                .toDomain()

    override fun all(): List<Product> =
        repository.findAll()
                .map { it.toDomain() }

}