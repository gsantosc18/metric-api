package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.User

class CreateNewUser(
    private val userService: UserService
): Command<User, User> {
    override fun  execute(command: User): User =
        userService.save(command)
}