package com.gedalias.metricasopentelemetry.application.service

import com.gedalias.metricasopentelemetry.domain.User

interface UserService {

    fun findBy(user: User?): List<User>

    fun save(user: User): User

    fun findById(id: String): User?
}