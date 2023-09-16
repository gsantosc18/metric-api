package com.gedalias.metricasopentelemetry.metrics.aspect

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.metrics.OrderMetrics
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class OrderAspect(
        private val orderMetrics: OrderMetrics
) {
    @After("@annotation(com.gedalias.metricasopentelemetry.metrics.annotation.OrderCounter)")
    fun sendMetric(joinPoint: JoinPoint) {
        joinPoint.args
                .filterIsInstance<Order>().first()
                .also(orderMetrics::incrementOrder)
    }
}