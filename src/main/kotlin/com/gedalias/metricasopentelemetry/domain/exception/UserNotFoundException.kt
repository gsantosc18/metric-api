package com.gedalias.metricasopentelemetry.domain.exception

class UserNotFoundException(
        override val message: String?
): Exception(message)