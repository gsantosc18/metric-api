package com.gedalias.metricasopentelemetry.infra.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gedalias.metricasopentelemetry.domain.Status
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import com.gedalias.metricasopentelemetry.infra.database.repository.ProductRepository
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateOrderDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.UpdateOrderDTO
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    private val objectMapper = ObjectMapper()

    private lateinit var orderId: String
    private lateinit var userId: String
    private lateinit var productId: String


    @BeforeEach
    fun setUp() {
        val userEntity = UserEntity(id = null, name = "Jhon Doel", email = "jhon@email.com", birthday = LocalDate.parse("2023-01-01")).let(userRepository::save)
        val productEntity = ProductEntity(id = null, title = "Product test", description = null, value = BigDecimal.valueOf(1000)).let(productRepository::save)
        val orderEntity = OrderEntity(id = null, user = userEntity, products = listOf(productEntity), value = BigDecimal.valueOf(1000), status = Status.PENDING).let(orderRepository::save)
        userId = requireNotNull(userEntity.id)
        orderId = requireNotNull(orderEntity.id)
        productId = requireNotNull(productEntity.id)
        print("""
            orderId: $orderId,
            userId: $userId,
            products: [$productId]
        """.trimIndent())
    }

    @AfterEach
    fun down() {
        orderRepository.deleteAll()
        userRepository.deleteAll()
        productRepository.deleteAll()
    }

    @Test
    fun `Find user list without filter`() {
        mockMvc.perform(get("/order"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andDo(print())
    }

    @Test
    fun `Find user list by filter`() {
        mockMvc.perform(get("/order").param("status", "PENDING"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", Matchers.`is`(1)))
            .andDo(print())
    }

    @Test
    fun `Return empty if not fount filter`() {
        mockMvc.perform(get("/order").param("status", "CANCELLED"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", Matchers.`is`(0)))
            .andDo(print())
    }

    @Test
    fun `Create new order`() {
        val createOrderDTO = CreateOrderDTO(
            user = userId,
            products = listOf(productId),
            value = BigDecimal.valueOf(100)
        )
        mockMvc.perform(
                post("/order")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(createOrderDTO))
            )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.id",Matchers.notNullValue()))
            .andExpect(jsonPath("$.user.id",Matchers.`is`(userId)))
            .andExpect(jsonPath("$.products[0].id",Matchers.`is`(productId)))
            .andDo(print())
    }

    @Test
    fun `Not create order if user not exist`() {
        val createOrderDTO = CreateOrderDTO(
            user = "111",
            products = listOf(orderId),
            value = BigDecimal.valueOf(100)
        )
        mockMvc.perform(
                post("/order")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(createOrderDTO))
            )
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.error",Matchers.`is`("User 111 not found")))
            .andDo(print())
    }

    @Test
    fun `Not create order if user was null`() {
        val invalidOrder = CreateOrderDTO(
            user = null,
            products = listOf(productId),
            value = BigDecimal.TEN
        )
        mockMvc.perform(
            post("/order")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(invalidOrder))
        )
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.errors.length()", Matchers.`is`(1)))
            .andExpect(jsonPath("$.errors[0]",Matchers.`is`("User is required")))
            .andDo(print())
    }

    @Test
    fun `Not create order if product list was empty`() {
        val invalidOrder = CreateOrderDTO(
            user = userId,
            products = emptyList(),
            value = BigDecimal.TEN
        )
        mockMvc.perform(
            post("/order")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(invalidOrder))
        )
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.errors.length()", Matchers.`is`(1)))
            .andExpect(jsonPath("$.errors[0]",Matchers.`is`("Product list can not be empty")))
            .andDo(print())
    }

    @Test
    fun `Not create order if value was null`() {
        val invalidOrder = CreateOrderDTO(
            user = userId,
            products = listOf(productId),
            value = null
        )
        mockMvc.perform(
                post("/order")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(invalidOrder))
            )
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.errors.length()", Matchers.`is`(1)))
            .andExpect(jsonPath("$.errors[0]",Matchers.`is`("Value is required")))
            .andDo(print())
    }

    @Test
    fun `Update order`() {
        val updateOrderDTO = UpdateOrderDTO(
            id = orderId,
            products = listOf(productId),
            user = userId,
            status = Status.CANCELLED,
            value = BigDecimal.valueOf(1000)
        )
        mockMvc.perform(put("/order")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateOrderDTO))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", Matchers.`is`("CANCELLED")))
            .andDo(print())
    }

    @Test
    fun `Not update if order not fount`() {
        val updateOrderDTO = UpdateOrderDTO(
            id = "111",
            products = listOf(productId),
            user = userId,
            status = Status.CANCELLED,
            value = BigDecimal.valueOf(1000)
        )
        mockMvc.perform(put("/order")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateOrderDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error", Matchers.`is`("Order 111 not found")))
            .andDo(print())
    }

    @Test
    fun `Not update if order id is null`() {
        val updateOrderDTO = UpdateOrderDTO(
            id = null,
            products = null,
            user = null,
            status = null,
            value = null
        )
        mockMvc.perform(put("/order")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateOrderDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors.length()", Matchers.`is`(5)))
            .andExpect(jsonPath("$.errors", Matchers.containsInAnyOrder("Id is required","Status is required","User is required","Value is required","Product list not be empty")))
            .andDo(print())
    }
}