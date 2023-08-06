package com.gedalias.metricasopentelemetry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MetricasOpentelemetryApplication

fun main(args: Array<String>) {
	runApplication<MetricasOpentelemetryApplication>(*args)
}
