package com.gedalias.metricasopentelemetry.domain.dto

import java.time.LocalDate

data class UserDTO(
        val name: String,
        val email: String,
        val birthday: LocalDate
)