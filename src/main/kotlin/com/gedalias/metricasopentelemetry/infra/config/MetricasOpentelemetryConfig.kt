package com.gedalias.metricasopentelemetry.infra.config

import com.gedalias.metricasopentelemetry.application.service.OrderStatusService
import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.application.usecase.*
import com.gedalias.metricasopentelemetry.infra.service.OrderService
import com.gedalias.metricasopentelemetry.infra.service.ProductService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricasOpentelemetryConfig {

    @Bean
    fun createNewUser(
        userService: UserService
    ): CreateNewUser = CreateNewUser(userService)

    @Bean
    fun listUsers(
        userService: UserService
    ): ListUsers = ListUsers(userService)

    @Bean
    fun listOrder(
        orderService: OrderService
    ): ListOrders = ListOrders(orderService)

    @Bean
    fun createNewOrder(
        orderService: OrderService,
        userService: UserService
    ): CreateNewOrder = CreateNewOrder(orderService, userService)

    @Bean
    fun createNewProduct(
        productService: ProductService
    ): CreateNewProduct = CreateNewProduct(productService)

    @Bean
    fun listProducts(
        productService: ProductService
    ): ListProducts = ListProducts(productService)

    @Bean
    fun createNewOrderStatus(
        orderService: OrderService,
        orderStatusService: OrderStatusService
    ): CreateNewOrderStatus = CreateNewOrderStatus(orderService, orderStatusService)

    @Bean
    fun updateOrder(
        orderService: OrderService
    ): UpdateOrder = UpdateOrder(orderService)
}