package com.gedalias.metricasopentelemetry.infra.instrumentation.aspect

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.infra.instrumentation.OrderCounterInstrumentation
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class OrderAspect(
        private val orderCounterInstrumentation: OrderCounterInstrumentation
) {
    @After("@annotation(com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.OrderCounter)")
    fun sendMetric(joinPoint: JoinPoint) {
        joinPoint.args
                .filterIsInstance<Order>().first()
                .also(orderCounterInstrumentation::incrementOrder)
    }
}