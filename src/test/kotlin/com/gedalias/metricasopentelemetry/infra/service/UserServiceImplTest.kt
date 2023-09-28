package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.sql.SQLIntegrityConstraintViolationException

class UserServiceImplTest {
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserServiceImpl(userRepository)

    @Test
    fun `Save new user`() {
        val user = User.create()

        every { userRepository.save(any()) } returns user.toEntity()

        userService.save(user)

        verify { userRepository.save(any<UserEntity>()) }
    }

    @Test
    fun `Not save if repository throw exception`() {
        every { userRepository.save(any()) } throws SQLIntegrityConstraintViolationException()

        assertThrows<SQLIntegrityConstraintViolationException> { userService.save(User.create()) }

        verify { userRepository.save(any()) }
    }
}