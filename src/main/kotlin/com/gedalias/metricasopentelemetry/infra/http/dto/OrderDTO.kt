package com.gedalias.metricasopentelemetry.infra.http.dto

import com.gedalias.metricasopentelemetry.domain.Status
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

data class CreateOrderDTO(
    @field:NotNull(message = "User is required") val user: String?,
    @field:NotEmpty(message = "Product list can not be empty") val products: List<String>?,
    @field:NotNull(message = "Value is required") val value: BigDecimal?
)

data class OrderDTO(
    val id: String?,
    val user: UserDTO?,
    val products: List<ProductDTO>?,
    val value: BigDecimal?,
    val status: Status?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)

data class UpdateOrderDTO(
    @field:NotNull(message = "Id is required") val id: String?,
    @field:NotNull(message = "User is required") val user: String?,
    @field:NotEmpty(message = "Product list not be empty") val products: List<String>?,
    @field:NotNull(message = "Status is required") val status: Status?,
    @field:NotNull(message = "Value is required") val value: BigDecimal?
)
