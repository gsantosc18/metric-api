package com.gedalias.metricasopentelemetry.infra.http.dto

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal


data class ProductDTO(
    val id: String?,
    val title: String?,
    val description: String?,
    val value: BigDecimal?
)

data class CreateProductDTO(
    @field:NotNull(message = "Title is required") val title: String?,
    val description: String?,
    @field:NotNull(message = "Value is required") val value: BigDecimal?
)