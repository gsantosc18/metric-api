package com.gedalias.metricasopentelemetry.metrics

import com.gedalias.metricasopentelemetry.domain.Order

interface OrderMetrics {

    fun incrementOrder(order: Order)

}