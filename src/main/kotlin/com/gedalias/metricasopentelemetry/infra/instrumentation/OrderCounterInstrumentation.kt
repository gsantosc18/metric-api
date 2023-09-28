package com.gedalias.metricasopentelemetry.infra.instrumentation

import com.gedalias.metricasopentelemetry.domain.Order

interface OrderCounterInstrumentation {

    fun incrementOrder(order: Order)

}