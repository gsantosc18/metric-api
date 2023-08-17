package com.gedalias.metricasopentelemetry.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "user")
data class UserEntity(
        @GeneratedValue(strategy = GenerationType.UUID)
        @Id val id: UUID?,
        val name: String,
        @Column(unique = true)
        val email: String,
        val birthday: LocalDate
)