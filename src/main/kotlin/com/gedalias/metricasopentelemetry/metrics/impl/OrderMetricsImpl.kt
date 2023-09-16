package com.gedalias.metricasopentelemetry.metrics.impl

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.metrics.OrderMetrics
import io.opentelemetry.api.common.AttributeKey.stringKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.common.Attributes.of
import io.opentelemetry.api.metrics.LongCounter
import io.opentelemetry.api.metrics.Meter
import org.springframework.stereotype.Component

@Component
class OrderMetricsImpl(
        meter: Meter
): OrderMetrics {
    private lateinit var orderCounter: LongCounter

    init {
        orderCounter = meter.counterBuilder("order_count")
                .setDescription("Quantidade de compras")
                .setUnit("1")
                .build()
    }

    override fun incrementOrder(order: Order) {
        orderCounter.add(1, order.attributes())
    }

    fun Order.attributes(): Attributes = of(stringKey("status"), status!!.name)
}
