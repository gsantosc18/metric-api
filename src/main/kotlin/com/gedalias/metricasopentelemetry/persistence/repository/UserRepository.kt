package com.gedalias.metricasopentelemetry.persistence.repository

import com.gedalias.metricasopentelemetry.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<UserEntity, UUID>