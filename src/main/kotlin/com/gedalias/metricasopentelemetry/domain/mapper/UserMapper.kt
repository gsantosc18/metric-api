package com.gedalias.metricasopentelemetry.domain.mapper

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.domain.dto.UserDTO


fun UserDTO.toDomain() = User(
        name = name,
        email = email,
        birthday = birthday
)