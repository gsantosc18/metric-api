package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDate
import java.util.UUID

data class User(
        val id: UUID?,
        val name: String,
        val email: String,
        val birthday: LocalDate
)