package com.gedalias.metricasopentelemetry.domain

import java.math.BigDecimal

data class Product(
        val id: String?,
        val title: String?,
        val description: String?,
        val value: BigDecimal?
) {

    companion object {
        fun create(
            id: String? = null,
            title: String? = null,
            description: String? = null,
            value: BigDecimal? = null
        ): Product = Product(
            id = id,
            title = null,
            description = null,
            value = null
        )
    }

}
