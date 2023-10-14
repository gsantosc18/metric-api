package com.gedalias.metricasopentelemetry.infra.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gedalias.metricasopentelemetry.infra.database.entity.ProductEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.ProductRepository
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateProductDTO
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var productRepository: ProductRepository

    private val objectMapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        ProductEntity(
            id = null, title = "Product test",
            description = "My description test",
            value = BigDecimal.valueOf(100)
        ).also(productRepository::save)
    }

    @AfterEach
    fun down() {
        productRepository.deleteAll()
    }

    @Test
    fun `Create new product`() {
        val createNewProductDTO = CreateProductDTO(
            title = "Product test",
            description = "My description test",
            value = BigDecimal.valueOf(100)
        )
        mockMvc.perform(post("/product")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewProductDTO))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", Matchers.notNullValue()))
            .andExpect(jsonPath("$.title", Matchers.`is`(createNewProductDTO.title)))
            .andExpect(jsonPath("$.description", Matchers.`is`(createNewProductDTO.description)))
            .andExpect(jsonPath("$.value", Matchers.`is`(createNewProductDTO.value!!.toInt())))
            .andDo(print())
    }

    @Test
    fun `Not create product if title is null`() {
        val createNewProductDTO = CreateProductDTO(
            title = null,
            description = "My description test",
            value = BigDecimal.valueOf(100)
        )
        mockMvc.perform(post("/product")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewProductDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors", Matchers.notNullValue()))
            .andExpect(jsonPath("$.errors[0]", Matchers.`is`("Title is required")))
            .andDo(print())
    }

    @Test
    fun `Not create product if value is null`() {
        val createNewProductDTO = CreateProductDTO(
            title = "Product test",
            description = "My description test",
            value = null
        )
        mockMvc.perform(post("/product")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createNewProductDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors", Matchers.notNullValue()))
            .andExpect(jsonPath("$.errors[0]", Matchers.`is`("Value is required")))
            .andDo(print())
    }

    @Test
    fun `List all products`() {
        mockMvc.perform(get("/product"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()", Matchers.`is`(1)))
            .andDo(print())
    }

    @Test
    fun `List products by filter`() {
        mockMvc.perform(get("/product").param("title", "Product test"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()", Matchers.`is`(1)))
            .andDo(print())
    }

    @Test
    fun `Return empty list if not found find by filter`() {
        mockMvc.perform(get("/product").param("title", "Test"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()", Matchers.`is`(0)))
            .andDo(print())
    }
}