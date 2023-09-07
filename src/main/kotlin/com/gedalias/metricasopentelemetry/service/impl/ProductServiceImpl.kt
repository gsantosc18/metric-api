package com.gedalias.metricasopentelemetry.service.impl

import com.gedalias.metricasopentelemetry.domain.dto.CreateProductDTO
import com.gedalias.metricasopentelemetry.domain.dto.ProductDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDomain
import com.gedalias.metricasopentelemetry.persistence.ProductPersistence
import com.gedalias.metricasopentelemetry.service.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        private val productPersistence: ProductPersistence
): ProductService {

    override fun create(createProductDTO: CreateProductDTO): ProductDTO =
        createProductDTO.toDomain()
                .let(productPersistence::save)
                .toDTO()

    override fun all(): List<ProductDTO> =
            productPersistence.all()
                    .map { it.toDTO() }

}