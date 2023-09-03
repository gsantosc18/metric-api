package com.gedalias.metricasopentelemetry.config

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory


class LogAppender: AppenderBase<ILoggingEvent>() {
    companion object {
        private const val SYSTEM_LOG = "SYSTEM_LOG"
    }
    private val objectMapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(SYSTEM_LOG)

    override fun append(event: ILoggingEvent?) {
        val log = Log(event)
        logger.info(objectMapper.writeValueAsString(log))
    }

    private data class Log(
            @JsonAlias("log_level")
            val level: String?,
            val loggerName: String?,
            val message: String?
    ) {
        constructor(event: ILoggingEvent?): this(
                level = event?.level?.levelStr,
                message = event?.message,
                loggerName = event?.loggerName
        )
    }
}