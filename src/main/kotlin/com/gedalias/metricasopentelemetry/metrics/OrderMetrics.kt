package com.gedalias.metricasopentelemetry.metrics

import com.gedalias.metricasopentelemetry.domain.dto.OrderDTO

interface OrderMetrics {

    fun incrementOrder(orderDTO: OrderDTO)

}