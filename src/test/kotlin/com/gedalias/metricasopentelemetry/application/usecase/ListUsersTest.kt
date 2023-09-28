package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListUsersTest {
    private val userService: UserService = mockk(relaxed = true)
    private val listUsers = ListUsers(userService)

    @Test
    fun `Should return list of users`() {
        val users = listOf(
            User.create(), User.create(), User.create()
        )

        every { userService.findBy(any()) } returns users

        val usersRecord = listUsers.execute(null)

        verify { userService.findBy(any()) }
        assertEquals(3, users.size)
    }
}