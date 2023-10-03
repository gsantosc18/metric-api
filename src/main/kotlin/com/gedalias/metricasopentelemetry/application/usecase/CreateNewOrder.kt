package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.OrderService
import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.domain.exception.UserNotFoundException


class CreateNewOrder(
    private val orderService: OrderService,
    private val userService: UserService
): Command<Order, Order> {
    override fun execute(command: Order): Order {
        checkIfUserExist(command.user?.id)
        return orderService.save(command)
    }

    private fun checkIfUserExist(userId: String?) {
        userId?.let(userService::findById) ?: throw UserNotFoundException("User $userId not found")
    }
}