package com.gedalias.metricasopentelemetry.controller

import com.gedalias.metricasopentelemetry.domain.dto.CreateProductDTO
import com.gedalias.metricasopentelemetry.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(
        private val productService: ProductService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping
    fun listAll(): ResponseEntity<*> = try {
        productService.all()
                .let { ok().body(it) }
    } catch (ex: Exception) {
        status(INTERNAL_SERVER_ERROR)
                .body(mapOf(
                        "error" to ex.message,
                        "trace" to ex.stackTrace
                ))
    }

    @PostMapping
    fun create(@RequestBody createProductDTO: CreateProductDTO): ResponseEntity<*> = try {
        logger.info("Start creation product: {}", createProductDTO)
        productService.create(createProductDTO)
                .also {
                    logger.info("Product created with success : {}", it)
                }
                .let {
                    status(CREATED).body(it)
                }
    } catch (ex: Exception) {
        status(INTERNAL_SERVER_ERROR)
                .body(mapOf(
                        "error" to ex.message,
                        "trace" to ex.stackTrace
                ))
    }

}