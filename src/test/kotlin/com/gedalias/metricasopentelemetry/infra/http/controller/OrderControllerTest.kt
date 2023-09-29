package com.gedalias.metricasopentelemetry.infra.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gedalias.metricasopentelemetry.domain.Status
import com.gedalias.metricasopentelemetry.infra.database.entity.OrderEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.OrderRepository
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateOrderDTO
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var orderRepository: OrderRepository

    private val id: String = UUID.randomUUID().toString()

    @BeforeEach
    fun setUp() {
        val userEntity = UserEntity(id = id, name = "Jhon Doel", email = "jhon@email.com", birthday = LocalDate.parse("2023-01-01"))
        val productEntity = ProductEntity(id = id, title = "Product test", description = null, value = BigDecimal.valueOf(1000))
        val orderEntity = OrderEntity(id = id, user = userEntity, products = listOf(productEntity), value = BigDecimal.valueOf(1000), status = Status.PENDING)
        orderRepository.save(orderEntity)
    }

    @AfterEach
    fun down() {
        orderRepository.findAll().map { orderRepository.delete(it) }
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
            user = id,
            products = listOf(id),
            value = BigDecimal.valueOf(100)
        )
        mockMvc.perform(
                post("/order")
                    .contentType(APPLICATION_JSON)
                    .content(ObjectMapper().writeValueAsBytes(createOrderDTO))
            )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.id",Matchers.notNullValue()))
            .andExpect(jsonPath("$.user.id",Matchers.`is`(id)))
            .andDo(print())
    }
}