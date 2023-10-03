package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Example
import org.springframework.data.repository.findByIdOrNull
import java.sql.SQLIntegrityConstraintViolationException

class UserServiceTest {
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserService(userRepository)

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

    @Test
    fun `Find users by filter`() {
        val userEntity = UserEntity(id = null, name = null, birthday = null, email = null)

        every { userRepository.findAll(any<Example<UserEntity>>()) } returns listOf(userEntity)

        val findBy = userService.findBy(User.create(name = "Jhon Doel"))

        verify { userRepository.findAll(any<Example<UserEntity>>()) }

        assertEquals(1, findBy.size)
    }

    @Test
    fun `Find users without filter`() {
        val userEntity = UserEntity(id = null, name = null, birthday = null, email = null)

        every { userRepository.findAll() } returns listOf(userEntity)

        val findBy = userService.findBy(null)

        verify { userRepository.findAll() }

        assertEquals(1, findBy.size)
    }

    @Test
    fun `Find user by id and return domain`() {
        val userId = "111"

        val userEntity = UserEntity(id = userId, name = null, birthday = null, email = null)

        every { userRepository.findByIdOrNull(userId) } returns userEntity

        val findById = userService.findById(userId)

        assertNotNull(findById)
        assertEquals(userId, findById?.id)
    }

    @Test
    fun `Find user by id and return null if not found`() {
        every { userRepository.findByIdOrNull("111") } returns null
        assertNull(userService.findById("111"))
    }
}