package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.User

class ListUsers(
    private val userService: UserService
): Command<User?, List<User>> {
    override fun execute(command: User?): List<User> =
        userService.findBy(command)
}