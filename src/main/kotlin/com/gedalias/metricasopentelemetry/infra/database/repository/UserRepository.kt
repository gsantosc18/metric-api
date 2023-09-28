package com.gedalias.metricasopentelemetry.infra.database.repository

import com.gedalias.metricasopentelemetry.infra.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, String>
