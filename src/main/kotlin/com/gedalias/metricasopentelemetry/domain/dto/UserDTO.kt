package com.gedalias.metricasopentelemetry.domain.dto

import java.time.LocalDate
import java.util.*

data class UserDTO(
        val id: UUID?,
        val name: String,
        val email: String,
        val birthday: LocalDate
)

data class CreateUserDTO(
        val name: String,
        val email: String,
        val birthday: LocalDate
)

data class UpdateUserDTO(
        val name: String?,
        val email: String?,
        val birthday: LocalDate?
)