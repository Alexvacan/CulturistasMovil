package com.easypark.app.registervehicle.data.repository

import com.easypark.app.registervehicle.domain.model.RegisterVehicleModel
import com.easypark.app.registervehicle.domain.repository.RegisterVehicleRepository

class RegisterVehicleRepositoryImpl : RegisterVehicleRepository {

    override suspend fun registerVehicle(data: RegisterVehicleModel): Boolean {

        if (data.plate.isBlank()) return false
        if (data.model.isBlank()) return false
        if (data.color.isBlank()) return false

        return true
    }
}