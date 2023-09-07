package com.gedalias.metricasopentelemetry.metrics.impl

import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO
import com.gedalias.metricasopentelemetry.metrics.OrderMetrics
import io.opentelemetry.api.common.AttributeKey.stringKey
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

    override fun incrementOrder(orderDTO: OrderDTO) {
        orderCounter.add(1, of(stringKey("status"), orderDTO.status!!.name))
    }

}