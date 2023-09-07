package com.gedalias.metricasopentelemetry.service

import com.gedalias.metricasopentelemetry.domain.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UpdateUserDTO
import com.gedalias.metricasopentelemetry.domain.dto.UserDTO

interface UserService {

    fun listAll(): List<UserDTO>

    fun save(createUserDTO: CreateUserDTO)

    fun update(id: String, updateUserDTO: UpdateUserDTO)

    fun remove(id: String)
}