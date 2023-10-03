package com.gedalias.metricasopentelemetry.infra.config

import com.gedalias.metricasopentelemetry.domain.exception.OrderNotFoundException
import com.gedalias.metricasopentelemetry.domain.exception.OrderStatusNotPendingException
import com.gedalias.metricasopentelemetry.domain.exception.UserNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice  {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<*> {
        val errors = ex.bindingResult.allErrors.map { it.defaultMessage }
        return ResponseEntity.badRequest().body(mapOf("errors" to errors))
    }

    @ExceptionHandler(
        OrderNotFoundException::class,
        OrderStatusNotPendingException::class,
        UserNotFoundException::class
    )
    fun handleException(ex: Exception): ResponseEntity<Map<String, String?>> =
        ResponseEntity.badRequest().body(mapOf("error" to ex.message))
}