package com.gedalias.metricasopentelemetry.infra.instrumentation.aspect

import com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.State.*
import com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.UserCounter
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class UserAspect(
        private val userCounter: com.gedalias.metricasopentelemetry.infra.instrumentation.UserCounterInstrumentation
) {

    @After("@annotation(com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.UserCounter)")
    fun afterUserPersistence(joinPoint: JoinPoint) {
        val signature = joinPoint.signature as MethodSignature
        val annotation = signature.method.getAnnotation(UserCounter::class.java)
        when(annotation.value) {
            CREATE -> userCounter.incrementUserRegistered()
            UPDATE -> userCounter.incrementUserUpdated()
            DELETE -> userCounter.incrementUserRemoved()
        }
    }

}