package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewProduct
import com.gedalias.metricasopentelemetry.application.usecase.ListProducts
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateProductDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.ProductDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDomain
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
    private val createNewProduct: CreateNewProduct,
    private val listProducts: ListProducts
) {
    @GetMapping
    fun listAll(productDTO: ProductDTO): ResponseEntity<*> =
        ok().body(listProducts.execute(productDTO.toDomain()))

    @PostMapping
    fun create(@RequestBody @Valid createProductDTO: CreateProductDTO): ResponseEntity<*> =
        status(HttpStatus.CREATED).body(createNewProduct.execute(createProductDTO.toDomain()))

}