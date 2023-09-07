package com.gedalias.metricasopentelemetry.controller

import com.gedalias.metricasopentelemetry.domain.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateUserDTO
import com.gedalias.metricasopentelemetry.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun allUsers(): ResponseEntity<*> = ResponseEntity.ok(userService.listAll()).also {
        logger.info("Fetch all users: size={}", it.body?.size)
    }

    @PostMapping
    fun createUser(@RequestBody createUserDTO: CreateUserDTO): ResponseEntity<*> =
        try {
            logger.info("Start user creation: {}", createUserDTO)
            userService.save(createUserDTO)
            logger.info("User created success: {}", createUserDTO)
            ResponseEntity.status(HttpStatus.CREATED).build<Any>()
        } catch (ex: Exception) {
            logger.info("Error on to create user: {}", createUserDTO, ex)
            ResponseEntity.internalServerError().body(ex.message)
        }

    @PatchMapping("/{userId}")
    fun updateUser(@PathVariable("userId") userId: String, @RequestBody updateUserDTO: UpdateUserDTO): ResponseEntity<*> =
            try {
                logger.info("Start to update user: userId={}, {}", userId, updateUserDTO)
                userService.update(userId, updateUserDTO)
                logger.info("User updated success: userId={}, {}", userId, updateUserDTO)
                ResponseEntity.ok().build<Any>()
            } catch (ex: Exception) {
                logger.error("Error on to update user: userId={}, {}", userId, updateUserDTO, ex)
                ResponseEntity.internalServerError().body(ex.message)
            }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable("userId") userId: String): ResponseEntity<*> =
            try {
                logger.info("Start to delete user: userId={}", userId)
                userService.remove(userId)
                logger.info("User deleted success: userId={}", userId)
                ResponseEntity.status(HttpStatus.OK).build<Any>()
            } catch (ex: Exception) {
                logger.error("Error on to delete user: userId={}", userId, ex)
                ResponseEntity.internalServerError().body(ex.message)
            }
}