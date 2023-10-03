package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewOrderStatus
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateNewOrderStatusDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.OrderStatusDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDomain
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order_status")
class OrderStatusController(
    private val createNewOrderStatus: CreateNewOrderStatus
) {

    @PostMapping
    fun create(@RequestBody createNewOrderStatusDTO: CreateNewOrderStatusDTO): ResponseEntity<OrderStatusDTO> {
        val orderStatus = createNewOrderStatus.execute(createNewOrderStatusDTO.toDomain())
        return status(HttpStatus.CREATED).body(orderStatus.toDTO())
    }

}