package com.gedalias.metricasopentelemetry.persistence.impl

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.persistence.UserPersistence
import com.gedalias.metricasopentelemetry.persistence.mapper.toDomain
import com.gedalias.metricasopentelemetry.persistence.mapper.toEntity
import com.gedalias.metricasopentelemetry.persistence.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserPersistenceImpl(
        private val userRepository: UserRepository
): UserPersistence {

    override fun listAll(): List<User> =
            userRepository.findAll().map { it.toDomain() }

    override fun save(user: User) {
        user.toEntity()
                .also(userRepository::save)
    }

    override fun findById(id: UUID): User? =
            userRepository.findByIdOrNull(id)?.toDomain()

}