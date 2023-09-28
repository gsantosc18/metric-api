package com.gedalias.metricasopentelemetry.infra.config

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean

//@Configuration
class LoggerConfig {
    @Bean
    fun logger(): Logger {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        return context.getLogger(Logger.ROOT_LOGGER_NAME)
            .apply {
                addAppender(buildLogAppender(context))
           }
    }

    private fun buildLogAppender(context: LoggerContext) =
        LogAppender()
            .apply {
                this.context = context
                start()
            }
}