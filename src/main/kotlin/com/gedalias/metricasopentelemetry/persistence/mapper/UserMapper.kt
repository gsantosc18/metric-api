package com.gedalias.metricasopentelemetry.persistence.mapper

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.persistence.entity.UserEntity

fun User.toEntity() = UserEntity(
        id = id,
        name = name,
        email = email,
        birthday = birthday
)

fun UserEntity.toDomain() = User(
        id = id,
        name = name,
        email = email,
        birthday = birthday
)