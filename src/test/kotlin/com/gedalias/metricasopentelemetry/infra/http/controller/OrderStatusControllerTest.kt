package com.gedalias.metricasopentelemetry.infra.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gedalias.metricasopentelemetry.domain.Status
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import com.gedalias.metricasopentelemetry.infra.database.repository.ProductRepository
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateNewOrderStatusDTO
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class OrderStatusControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    private val objectMapper = ObjectMapper()

    private lateinit var userEntity: UserEntity
    private lateinit var orderEntity: OrderEntity
    private lateinit var productEntity: ProductEntity


    @BeforeEach
    fun setUp() {
        userEntity = UserEntity(
            id = null,
            name = "John Doel",
            email = "jhon@email.com",
            birthday = LocalDate.parse("2023-01-01")
        ).let(userRepository::save)

        productEntity = ProductEntity(
            id = null,
            title = "Product test",
            description = null,
            value = BigDecimal.valueOf(1000)
        ).let(productRepository::save)

        orderEntity = OrderEntity(
            id = null,
            user = userEntity,
            products = listOf(productEntity),
            value = BigDecimal.valueOf(1000),
            status = Status.PENDING
        ).let(orderRepository::save)
    }

    @AfterEach
    fun down() {
        orderRepository.deleteAll()
        productRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `Create new order status record`() {
        val createNewOrderStatusDTO = CreateNewOrderStatusDTO(
            orderId = orderEntity.id,
            description = "Minha descrição"
        )
        mockMvc.perform(post("/order_status")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewOrderStatusDTO))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.orderId", Matchers.`is`(orderEntity.id)))
            .andExpect(jsonPath("$.createdAt", Matchers.notNullValue()))
            .andDo(print())
    }

    @Test
    fun `Not create order status if not found order`() {
        val createNewOrderStatusDTO = CreateNewOrderStatusDTO(
            orderId = "123",
            description = "Minha descrição"
        )
        mockMvc.perform(post("/order_status")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewOrderStatusDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error", Matchers.`is`("Order 123 not found")))
            .andDo(print())
    }

    @Test
    fun `Not create order status if order is not PENDING`() {
        val orderEntity = OrderEntity(
            id = null,
            user = userEntity,
            products = listOf(productEntity),
            value = BigDecimal.valueOf(1000),
            status = Status.CANCELLED
        ).let(orderRepository::save)

        val createNewOrderStatusDTO = CreateNewOrderStatusDTO(
            orderId = orderEntity.id,
            description = "Minha descrição"
        )
        mockMvc.perform(post("/order_status")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewOrderStatusDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error", Matchers.`is`("Order ${orderEntity.id} is not pending")))
            .andDo(print())
    }
}