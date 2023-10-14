package com.gedalias.metricasopentelemetry.domain

import java.time.LocalDate

data class User(
    val id: String?,
    val name: String?,
    val email: String?,
    val birthday: LocalDate?
) {

    companion object {
        fun create(
            id: String? = null,
            name: String? = null,
            email: String? = null,
            birthday: LocalDate? = null
        ) = User(
            id = id,
            name = name,
            email = email,
            birthday = birthday
        )
    }
}