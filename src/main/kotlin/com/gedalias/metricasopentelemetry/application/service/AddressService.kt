package com.gedalias.metricasopentelemetry.application.service

import com.gedalias.metricasopentelemetry.domain.Address

interface AddressService {

    fun findByUserId(userId: String): List<Address>

}