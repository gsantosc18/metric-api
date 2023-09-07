package com.gedalias.metricasopentelemetry.domain

import java.math.BigDecimal

data class Product(
        val id: String?,
        val title: String?,
        val description: String?,
        val value: BigDecimal?
) {

    constructor(id: String?): this(
            id = id,
            title = null,
            description = null,
            value = null
    )

}
