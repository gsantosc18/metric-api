package com.gedalias.metricasopentelemetry.service.impl

import com.gedalias.metricasopentelemetry.domain.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UserDTO
import com.gedalias.metricasopentelemetry.domain.exception.UserNotFoundException
import com.gedalias.metricasopentelemetry.domain.mapper.toDTO
import com.gedalias.metricasopentelemetry.domain.mapper.updateValues
import com.gedalias.metricasopentelemetry.persistence.UserPersistence
import com.gedalias.metricasopentelemetry.service.UserService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserServiceImpl(
        private val userPersistence: UserPersistence
): UserService {
    override fun listAll(): List<UserDTO> = userPersistence.listAll().map { it.toDTO() }

    override fun save(createUserDTO: CreateUserDTO) {
        userPersistence.save(createUserDTO.updateValues())
    }

    override fun update(id: UUID, updateUserDTO: UpdateUserDTO) {
        val user = userPersistence.findById(id)
                ?: throw UserNotFoundException("User not found : id=$id, name=${updateUserDTO.name}")
        updateUserDTO
                .updateValues(user)
                .also(userPersistence::save)
    }
}