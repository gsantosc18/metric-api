package com.gedalias.metricasopentelemetry.controller

import com.gedalias.metricasopentelemetry.domain.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateUserDTO
import com.gedalias.metricasopentelemetry.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {

    @GetMapping
    fun allUsers(): ResponseEntity<*> = ResponseEntity.ok(userService.listAll())

    @PostMapping
    fun createUser(@RequestBody createUserDTO: CreateUserDTO): ResponseEntity<*> =
        try {
            userService.save(createUserDTO)
            ResponseEntity.status(HttpStatus.CREATED).build<Any>()
        } catch (ex: Exception) {
            ResponseEntity.internalServerError().body(ex.message)
        }

    @PatchMapping("/{userId}")
    fun updateUser(@PathVariable("userId") userId: UUID, @RequestBody updateUserDTO: UpdateUserDTO): ResponseEntity<*> =
            try {
                userService.update(userId, updateUserDTO)
                ResponseEntity.ok().build<Any>()
            } catch (ex: Exception) {
                ResponseEntity.internalServerError().body(ex.message)
            }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable("userId") userId: UUID): ResponseEntity<*> =
            try {
                userService.remove(userId)
                ResponseEntity.status(HttpStatus.OK).build<Any>()
            } catch (ex: Exception) {
                ResponseEntity.internalServerError().body(ex.message)
            }
}