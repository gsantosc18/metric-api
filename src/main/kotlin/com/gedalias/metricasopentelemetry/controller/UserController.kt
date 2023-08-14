package com.gedalias.metricasopentelemetry.controller

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.domain.dto.UserDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    private var users: MutableList<User> = ArrayList()

    @GetMapping
    fun allUsers(): ResponseEntity<*> = ResponseEntity.ok(users)

    @PostMapping
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<*> {
        userDTO.toDomain().also(users::add)
        return ResponseEntity.ok(users)
    }

}