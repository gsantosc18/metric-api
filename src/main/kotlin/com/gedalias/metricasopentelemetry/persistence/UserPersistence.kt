package com.gedalias.metricasopentelemetry.persistence

import com.gedalias.metricasopentelemetry.domain.User

interface UserPersistence {

    fun listAll(): List<User>

    fun save(user: User)

    fun findById(id: String): User?

    fun remove(id: String)
}