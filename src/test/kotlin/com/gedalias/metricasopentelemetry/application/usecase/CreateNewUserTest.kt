package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.User
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CreateNewUserTest {
    private val userService: UserService = mockk(relaxed = true)
    private val createNewUser = CreateNewUser(userService)

    @Test
    fun `Should create a new user`() {
        val user = User.create()
        val foundUser = User.create(id = UUID.randomUUID().toString())

        every { userService.save(user) } returns foundUser

        val createdUser = createNewUser.execute(user)

        assertEquals(foundUser.id, createdUser.id)
    }

    @Test
    fun `Should not create user if exception is thrown when saving`() {
        every { userService.save(any()) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { createNewUser.execute(User.create()) }
    }
}