package com.gedalias.metricasopentelemetry.infra.service.mapper

import com.gedalias.metricasopentelemetry.domain.User
import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity

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