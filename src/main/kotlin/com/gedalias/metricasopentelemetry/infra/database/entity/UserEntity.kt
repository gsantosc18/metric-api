package com.gedalias.metricasopentelemetry.infra.database.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener::class)
data class UserEntity(
        @GeneratedValue(strategy = GenerationType.UUID)
        @Id val id: String?,
        val name: String?,
        @Column(unique = true)
        val email: String?,
        val birthday: LocalDate?,
        @OneToMany(mappedBy = "user")
        val orders: List<OrderEntity> = emptyList(),
        @CreatedDate
        var createdAt: LocalDateTime? = null,
        @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)