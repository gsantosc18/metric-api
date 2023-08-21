package com.gedalias.metricasopentelemetry.metrics

interface UserMetrics {
    fun incrementUserRegistered()

    fun incrementUserUpdated()
}