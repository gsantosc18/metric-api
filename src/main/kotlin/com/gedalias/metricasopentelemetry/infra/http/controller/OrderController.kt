package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewOrder
import com.gedalias.metricasopentelemetry.application.usecase.ListOrders
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.OrderDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDomain
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val listOrders: ListOrders,
    private val createNewOrder: CreateNewOrder
) {
    @GetMapping
    fun listAll(orderDTO: OrderDTO?): List<OrderDTO> =
        listOrders.execute(orderDTO?.toDomain())
            .map { it.toDTO() }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody createOrderDTO: CreateOrderDTO): ResponseEntity<*> =
        try {
            val createdOrder = createOrderDTO.toDomain().let(createNewOrder::execute)
            ResponseEntity.status(HttpStatus.CREATED).body(createdOrder)
        } catch (ex: Exception) {
            ResponseEntity.internalServerError().body(mapOf("error" to ex.message))
        }

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