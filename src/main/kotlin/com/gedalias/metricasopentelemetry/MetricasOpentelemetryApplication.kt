package com.gedalias.metricasopentelemetry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MetricasOpentelemetryApplication

fun main(args: Array<String>) {
	runApplication<MetricasOpentelemetryApplication>(*args)
}
