package com.gedalias.metricasopentelemetry.metrics.aspect

import com.gedalias.metricasopentelemetry.metrics.UserMetrics
import com.gedalias.metricasopentelemetry.metrics.annotation.State.*
import com.gedalias.metricasopentelemetry.metrics.annotation.UserCounter
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class UserAspect(
        private val userMetrics: UserMetrics
) {

    @After("@annotation(com.gedalias.metricasopentelemetry.metrics.annotation.UserCounter)")
    fun afterUserPersistence(joinPoint: JoinPoint) {
        val signature = joinPoint.signature as MethodSignature
        val annotation = signature.method.getAnnotation(UserCounter::class.java)
        when(annotation.value) {
            CREATE -> userMetrics.incrementUserRegistered()
            UPDATE -> userMetrics.incrementUserUpdated()
            DELETE -> userMetrics.incrementUserRemoved()
        }
    }

}