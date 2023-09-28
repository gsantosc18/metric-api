package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
        private val productService: ProductService
) {
//    companion object {
//        private val logger = LoggerFactory.getLogger(this::class.java)
//    }
//
//    @GetMapping
//    fun listAll(): ResponseEntity<*> = try {
//        productService.findBy()
//                .let { ok().body(it) }
//    } catch (ex: Exception) {
//        status(INTERNAL_SERVER_ERROR)
//                .body(mapOf(
//                        "error" to ex.message,
//                        "trace" to ex.stackTrace
//                ))
//    }
//
//    @PostMapping
//    fun create(@RequestBody createProductDTO: CreateProductDTO): ResponseEntity<*> = try {
//        logger.info("Start creation product: {}", createProductDTO)
//        productService.save(createProductDTO)
//                .also {
//                    logger.info("Product created with success : {}", it)
//                }
//                .let {
//                    status(CREATED).body(it)
//                }
//    } catch (ex: Exception) {
//        status(INTERNAL_SERVER_ERROR)
//                .body(mapOf(
//                        "error" to ex.message,
//                        "trace" to ex.stackTrace
//                ))
//    }

}