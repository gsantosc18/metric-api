package com.gedalias.metricasopentelemetry.domain

data class Address(
    val userId: String,
    val street: String?,
    val neighborhood: String?,
    val city: String?,
    val zipCode: String?,
    val number: String?,
    val complement: String?
) {
    companion object {
        fun create(
            userId: String,
            street: String? = null,
            neighborhood: String? = null,
            city: String? = null,
            zipCode: String? = null,
            number: String? = null,
            complement: String? = null
        ): Address = Address(
            userId = userId,
            street = street,
            neighborhood = neighborhood,
            city = city,
            zipCode = zipCode,
            number = number,
            complement = complement
        )
    }
}