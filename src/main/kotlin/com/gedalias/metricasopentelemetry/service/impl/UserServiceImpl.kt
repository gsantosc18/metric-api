package com.gedalias.metricasopentelemetry.service.impl

import com.gedalias.metricasopentelemetry.domain.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UserDTO
import com.gedalias.metricasopentelemetry.domain.exception.UserNotFoundException
import com.gedalias.metricasopentelemetry.domain.mapper.toDTO
import com.gedalias.metricasopentelemetry.domain.mapper.toDomain
import com.gedalias.metricasopentelemetry.metrics.UserMetrics
import com.gedalias.metricasopentelemetry.persistence.UserPersistence
import com.gedalias.metricasopentelemetry.service.UserService
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(
        private val userPersistence: UserPersistence,
        private val userMetrics: UserMetrics
): UserService {


    override fun listAll(): List<UserDTO> = userPersistence.listAll().map { it.toDTO() }

    override fun save(createUserDTO: CreateUserDTO) {
        createUserDTO.toDomain()
                .also(userPersistence::save)
                .also { userMetrics.incrementUserRegistered() }
    }

    override fun update(id: String, updateUserDTO: UpdateUserDTO) {
        val user = userPersistence.findById(id)
                ?: throw UserNotFoundException("User not found : id=$id, name=${updateUserDTO.name}")
        updateUserDTO
                .toDomain(user)
                .also(userPersistence::save)
                .also { userMetrics.incrementUserUpdated() }
    }

    override fun remove(id: String) {
        userPersistence.remove(id)
                .also{ userMetrics.incrementUserRemoved() }
    }
}