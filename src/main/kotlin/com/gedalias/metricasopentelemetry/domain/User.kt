package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDate

data class User(
        val id: String?,
        val name: String?,
        val email: String?,
        val birthday: LocalDate?
) {
    constructor(id: String?): this(
            id = id,
            name = null,
            email = null,
            birthday = null
    )
}