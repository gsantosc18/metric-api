package com.gedalias.metricasopentelemetry.infra.database.entity

import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "order_status")
@EntityListeners(AuditingEntityListener::class)
data class OrderStatusEntity(
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id val id: String?,
    val orderId: String?,
    val description: String?,
    @CreatedDate
    var createdAt: LocalDateTime?
)
