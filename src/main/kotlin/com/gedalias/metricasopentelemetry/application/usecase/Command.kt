package com.gedalias.metricasopentelemetry.application.usecase

interface Command<I,O> {
    fun execute(command: I): O

}