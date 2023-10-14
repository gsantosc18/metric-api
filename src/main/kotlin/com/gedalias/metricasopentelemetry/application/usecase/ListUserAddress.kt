package com.gedalias.metricasopentelemetry.application.usecase

import com.gedalias.metricasopentelemetry.application.service.AddressService
import com.gedalias.metricasopentelemetry.domain.Address

class ListUserAddress(
    private val addressService: AddressService
): Command<String, List<Address>> {
    override fun execute(command: String): List<Address> =
        addressService.findByUserId(command)
}