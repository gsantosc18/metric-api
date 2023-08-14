package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDate

data class User(
        val name: String,
        val email: String,
        val birthday: LocalDate
)