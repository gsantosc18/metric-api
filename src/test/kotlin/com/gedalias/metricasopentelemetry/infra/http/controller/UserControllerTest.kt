package com.gedalias.metricasopentelemetry.infra.http.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateUserDTO
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
import java.time.LocalDate

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userRepository: UserRepository

    private val objectMapper = ObjectMapper().also { it.registerModules(JavaTimeModule()) }

    @BeforeEach
    fun setUp() {
        UserEntity(
            id = null,
            name = "John Doel",
            email = "jhon@email.com",
            birthday = LocalDate.parse("2023-01-01")
        ).let(userRepository::save)
    }

    @AfterEach
    fun down() {
        userRepository.deleteAll()
    }

    @Test
    fun `Create new user and return status 201`() {
        val createUserDTO = CreateUserDTO(
            name = "Jhon Doel",
            email = "jhon1@email.com",
            birthday = LocalDate.parse("2023-01-01")
        )
        mockMvc.perform(post("/user")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createUserDTO))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", Matchers.notNullValue()))
            .andExpect(jsonPath("$.name", Matchers.`is`(createUserDTO.name)))
            .andExpect(jsonPath("$.email", Matchers.`is`(createUserDTO.email)))
            .andExpect(jsonPath("$.birthday", Matchers.`is`("2023-01-01")))
            .andDo(print())
    }

    @Test
    fun `Not create user if name is null`() {
        val createUserDTO = CreateUserDTO(
            name = null,
            email = "jhon@email.com",
            birthday = LocalDate.parse("2023-01-01")
        )
        mockMvc.perform(post("/user")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createUserDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors", Matchers.notNullValue()))
            .andExpect(jsonPath("$.errors[0]", Matchers.`is`("Name is required")))
            .andDo(print())
    }

    @Test
    fun `Not create user if email is null`() {
        val createUserDTO = CreateUserDTO(
            name = "Jhon Doel",
            email = null,
            birthday = LocalDate.parse("2023-01-01")
        )
        mockMvc.perform(post("/user")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createUserDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors", Matchers.notNullValue()))
            .andExpect(jsonPath("$.errors[0]", Matchers.`is`("Email is required")))
            .andDo(print())
    }

    @Test
    fun `Not create user if birthday is null`() {
        val createUserDTO = CreateUserDTO(
            name = "Jhon Doel",
            email = "jhon@email.com",
            birthday = null
        )
        mockMvc.perform(post("/user")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createUserDTO))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors", Matchers.notNullValue()))
            .andExpect(jsonPath("$.errors[0]", Matchers.`is`("Birthday is required")))
            .andDo(print())
    }

    @Test
    fun `List all saved users`() {
        mockMvc.perform(get("/user")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()",Matchers.`is`(1)))
            .andDo(print())
    }

    @Test
    fun `List users filtered`() {
        mockMvc.perform(get("/user")
            .contentType(APPLICATION_JSON)
            .param("name", "John Doel")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()",Matchers.`is`(1)))
            .andDo(print())
    }

    @Test
    fun `Return empty if not found users by filter`() {
        mockMvc.perform(get("/user")
            .contentType(APPLICATION_JSON)
            .param("name", "John")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()",Matchers.`is`(0)))
            .andDo(print())
    }
}