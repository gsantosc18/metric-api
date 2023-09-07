package com.gedalias.metricasopentelemetry.persistence.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener::class)
data class ProductEntity(
        @GeneratedValue(strategy = GenerationType.UUID)
        @Id val id: String?,
        val title: String?,
        val description: String?,
        val value: BigDecimal?,
        @CreatedDate
        var createdAt: LocalDateTime? = null,
        @LastModifiedDate
        var updatedAt: LocalDateTime? = null,
        @ManyToMany(mappedBy = "products")
        val orders: List<OrderEntity> = emptyList()
)