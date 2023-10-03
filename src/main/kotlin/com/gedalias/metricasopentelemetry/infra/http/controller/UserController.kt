package com.gedalias.metricasopentelemetry.infra.http.controller

import com.gedalias.metricasopentelemetry.application.usecase.CreateNewUser
import com.gedalias.metricasopentelemetry.application.usecase.ListUsers
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.UserDTO
import com.gedalias.metricasopentelemetry.infra.http.mapper.toDomain
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val createNewUser: CreateNewUser,
    private val listUsers: ListUsers
) {

    @GetMapping
    fun allUsers(userDTO: UserDTO): ResponseEntity<*> =
        ResponseEntity.ok(listUsers.execute(userDTO.toDomain()))

    @PostMapping
    fun createUser(@RequestBody @Valid createUserDTO: CreateUserDTO): ResponseEntity<*> =
        ResponseEntity.status(CREATED).body(createNewUser.execute(createUserDTO.toDomain()))
}