package com.gedalias.metricasopentelemetry.infra.instrumentation

interface UserCounterInstrumentation {
    fun incrementUserRegistered()

    fun incrementUserUpdated()

    fun incrementUserRemoved()
}