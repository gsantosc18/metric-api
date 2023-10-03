package com.gedalias.metricasopentelemetry.infra.service

import com.gedalias.metricasopentelemetry.application.service.UserService
import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.database.repository.UserRepository
import com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.State.CREATE
import com.gedalias.metricasopentelemetry.infra.instrumentation.annotation.UserCounter
import com.gedalias.metricasopentelemetry.infra.service.mapper.toDomain
import com.gedalias.metricasopentelemetry.infra.service.mapper.toEntity
import org.springframework.data.domain.Example
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
): UserService {
    override fun findBy(user: User?): List<User> =
        when {
            user != null -> userRepository.findAll(Example.of(user.toEntity()))
            else -> userRepository.findAll()
        }
        .map { it.toDomain() }

    @UserCounter(CREATE)
    override fun save(user: User): User =
        user.toEntity()
            .let(userRepository::save)
            .toDomain()

    override fun findById(id: String): User? =
        userRepository.findByIdOrNull(id)?.toDomain()

}