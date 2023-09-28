package com.gedalias.metricasopentelemetry.infra.database.entity

import com.gedalias.metricasopentelemetry.domain.Status
import jakarta.persistence.*
import jakarta.persistence.FetchType.EAGER
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "order_cart")
@EntityListeners(AuditingEntityListener::class)
data class OrderEntity(
    @GeneratedValue(strategy = GenerationType.UUID)
        @Id val id: String?,
    @ManyToOne(fetch = EAGER)
        @JoinColumn(name = "user_id", nullable = false)
        val user: UserEntity,
    @ManyToMany(fetch = EAGER)
        @JoinTable(
                name = "order_product",
                joinColumns = [ JoinColumn(name = "order_id") ],
                inverseJoinColumns = [ JoinColumn(name = "product_id") ]
        )
        var products: List<ProductEntity>?,
    val value: BigDecimal,
    @Enumerated(EnumType.STRING)
        val status: Status,
    @CreatedDate
        var createdAt: LocalDateTime? = null,
    @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)