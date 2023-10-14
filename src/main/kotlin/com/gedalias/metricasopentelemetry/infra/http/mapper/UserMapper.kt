package com.gedalias.metricasopentelemetry.infra.http.mapper

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.http.dto.CreateUserDTO
import com.gedalias.metricasopentelemetry.infra.http.dto.UserDTO

fun User.toDTO() = UserDTO(
    id = id,
    name = name,
    email = email,
    birthday = birthday
)

fun UserDTO.toDomain() = User(
    id = id,
    name = name,
    email = email,
    birthday = birthday
)

fun CreateUserDTO.toDomain() = User(
    id = null,
    name = name,
    email = email,
    birthday = birthday
)