package com.gedalias.metricasopentelemetry.persistence.impl

import com.gedalias.metricasopentelemetry.domain.Order
import com.gedalias.metricasopentelemetry.metrics.annotation.OrderCounter
import com.gedalias.metricasopentelemetry.persistence.OrderPersistence
import com.gedalias.metricasopentelemetry.persistence.mapper.toDomain
import com.gedalias.metricasopentelemetry.persistence.mapper.toEntity
import com.gedalias.metricasopentelemetry.persistence.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class OrderPersistenceImpl(
        private val repository: OrderRepository
): OrderPersistence {
    override fun all(): List<Order> =
            repository.findAll()
                    .map { it.toDomain() }

    @OrderCounter
    override fun save(order: Order): Order =
            order.toEntity()
                .let(repository::save)
                .toDomain()

    override fun findById(id: String): Order? =
            repository.findByIdOrNull(id)?.toDomain()

    @OrderCounter
    override fun update(id: String, order: Order) =
            repository.findByIdOrNull(id)
                    ?.let { o ->
                        o.copy(
                            id = id,
                            user = order.user?.toEntity() ?: o.user,
                            products = order.products?.map { it.toEntity() } ?: o.products,
                            status = order.status ?: o.status,
                            value = order.value ?: o.value
                        )
                    }
                    ?.let(repository::save)
                    ?.toDomain()
}