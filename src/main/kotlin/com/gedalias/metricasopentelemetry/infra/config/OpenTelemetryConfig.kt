package com.gedalias.metricasopentelemetry.infra.config

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.api.trace.Tracer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenTelemetryConfig {
    companion object {
        private const val PROJECT_NAME = "metric-app"
        private const val PROJECT_VERSION = "1.0.0"
    }

    @Bean
    fun tracer(): Tracer = GlobalOpenTelemetry.getTracer(PROJECT_NAME, PROJECT_VERSION)

    @Bean
    fun meter(): Meter = GlobalOpenTelemetry.meterBuilder(PROJECT_NAME)
            .setInstrumentationVersion(PROJECT_VERSION).build()
}