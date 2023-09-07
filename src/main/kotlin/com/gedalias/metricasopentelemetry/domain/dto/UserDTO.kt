package com.gedalias.metricasopentelemetry.domain.dto

import java.time.LocalDate

data class UserDTO(
        val id: String?,
        val name: String?,
        val email: String?,
        val birthday: LocalDate?
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