package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
        private val orderService: OrderService
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

//    @GetMapping
//    fun listAll(): List<OrderDTO> =
//            orderService.all()
//
//    @PostMapping
//    fun create(@RequestBody createOrderDTO: CreateOrderDTO): ResponseEntity<*> =
//            try {
//                logger.info("Start create new order: {}", createOrderDTO)
//                orderService.save(createOrderDTO)
//                        .also { logger.info("Created order with success: {}", it) }
//                        .let(status(HttpStatus.CREATED)::body)
//            } catch (ex: Exception) {
//                status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(mapOf(
//                                "error" to ex.message,
//                                "trace" to ex.stackTrace
//                        ))
//            }
//
//    @GetMapping("/{id}")
//    fun findById(@PathVariable("id") id: String): ResponseEntity<*> =
//            try {
//                orderService.findById(id)
//                        ?.let { ResponseEntity.ok().body(it) }
//                        ?: ResponseEntity.notFound().build<String>()
//            } catch (ex: Exception) {
//                status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(mapOf(
//                                "error" to ex.message,
//                                "trace" to ex.stackTrace
//                        ))
//            }
//
//    @PutMapping("/{id}")
//    fun update(@PathVariable("id") id: String, @RequestBody updateOrderDTO: UpdateOrderDTO): ResponseEntity<*> =
//            try {
//                orderService.update(id, updateOrderDTO)
//                        .let(ok()::body)
//            } catch (ex: Exception) {
//                status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(mapOf(
//                                "error" to ex.message,
//                                "trace" to ex.stackTrace
//                        ))
//            }
}