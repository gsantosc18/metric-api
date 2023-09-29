package com.gedalias.metricasopentelemetry.infra.config

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewOrder
import com.gedalias.metricasopentelemetry.application.usecase.ListOrders
import com.gedalias.metricasopentelemetry.infra.service.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricasOpentelemetryConfig {

    @Bean
    fun listOrder(
        orderService: OrderService
    ): ListOrders = ListOrders(orderService)

    @Bean
    fun createNewOrder(
        orderService: OrderService
    ): CreateNewOrder = CreateNewOrder(orderService)
}