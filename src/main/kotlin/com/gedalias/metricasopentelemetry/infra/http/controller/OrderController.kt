package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewOrder
import com.gedalias.metricasopentelemetry.application.usecase.ListOrders
import com.gedalias.metricasopentelemetry.application.usecase.UpdateOrder
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.OrderDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.UpdateOrderDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDomain
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/order")
class OrderController(
    private val listOrders: ListOrders,
    private val createNewOrder: CreateNewOrder,
    private val updateOrder: UpdateOrder
) {
    @GetMapping
    fun listAll(orderDTO: OrderDTO?): List<OrderDTO> =
        listOrders.execute(orderDTO?.toDomain()).map { it.toDTO() }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody @Valid createOrderDTO: CreateOrderDTO): ResponseEntity<*> {
        val createdOrder = createOrderDTO.toDomain().let(createNewOrder::execute)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder)
    }

    @PutMapping
    fun update(@RequestBody @Valid updateOrderDTO: UpdateOrderDTO): OrderDTO =
        updateOrder.execute(updateOrderDTO.toDomain()).toDTO()
}