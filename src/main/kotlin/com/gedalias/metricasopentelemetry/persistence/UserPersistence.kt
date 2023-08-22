package com.gedalias.metricasopentelemetry.persistence

import com.gedalias.metricasopentelemetry.domain.User
import java.util.UUID

interface UserPersistence {

    fun listAll(): List<User>

    fun save(user: User)

    fun findById(id: UUID): User?

    fun remove(id: UUID)
}