package com.gedalias.metricasopentelemetry.persistence.repository

import com.gedalias.metricasopentelemetry.persistence.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<ProductEntity, String>
