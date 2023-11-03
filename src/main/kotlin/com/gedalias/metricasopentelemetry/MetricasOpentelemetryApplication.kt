package com.gedalias.metricasopentelemetry

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MetricasOpentelemetryApplication

fun main(args: Array<String>) {
	runApplication<MetricasOpentelemetryApplication>(*args)
}
