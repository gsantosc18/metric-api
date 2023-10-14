package com.gedalias.metricasopentelemetry.infra.database.repository

import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<ProductEntity, String>
