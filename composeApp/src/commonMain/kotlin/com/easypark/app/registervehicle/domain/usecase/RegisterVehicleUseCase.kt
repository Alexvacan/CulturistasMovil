package com.easypark.app.registervehicle.domain.usecase

import com.easypark.app.registervehicle.domain.model.RegisterVehicleModel
import com.easypark.app.registervehicle.domain.repository.RegisterVehicleRepository

class RegisterVehicleUseCase(
    private val repository: RegisterVehicleRepository
) {

    suspend operator fun invoke(data: RegisterVehicleModel): Boolean {
        return repository.registerVehicle(data)
    }
}