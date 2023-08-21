package com.gedalias.metricasopentelemetry.metrics.impl

import com.gedalias.metricasopentelemetry.metrics.UserMetrics
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.metrics.LongCounter
import io.opentelemetry.api.metrics.Meter
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Component
class UserMetricsImpl(
        meter: Meter
): UserMetrics {
    private lateinit var userRegisteredCounter: LongCounter
    private lateinit var userUpdatedCounter: LongCounter

    companion object {
        private val MINUTE_PATTER = DateTimeFormatter.ofPattern("HH:mm")
    }

    init {
        userRegisteredCounter = meter.counterBuilder("users.registered")
                .setDescription("Número de usuários cadastrados")
                .setUnit("1")
                .build()

        userUpdatedCounter = meter.counterBuilder("users.updated")
                .setDescription("Quantidade de usuários atualizados")
                .setUnit("1")
                .build()
    }

    override fun incrementUserRegistered() {
        userRegisteredCounter.add(1, Attributes.of(AttributeKey.stringKey("hour"), LocalTime.now().format(MINUTE_PATTER)))
    }

    override fun incrementUserUpdated() {
        userUpdatedCounter.add(1, Attributes.of(AttributeKey.stringKey("hour"), LocalTime.now().format(MINUTE_PATTER)))
    }
}