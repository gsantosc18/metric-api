package com.gedalias.metricasopentelemetry.infra.http.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class UserDTO(
    val id: String?,
    val name: String?,
    val email: String?,
    val birthday: LocalDate?
)

data class CreateUserDTO(
    @field:NotBlank(message = "Name is required") val name: String?,
    @field:NotBlank(message = "Email is required") val email: String?,
    @field:NotNull(message = "Birthday is required")
    val birthday: LocalDate?
)
