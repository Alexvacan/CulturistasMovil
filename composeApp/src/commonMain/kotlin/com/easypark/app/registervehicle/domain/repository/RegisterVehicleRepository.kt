package com.easypark.app.registervehicle.domain.repository

import com.easypark.app.registervehicle.domain.model.RegisterVehicleModel

interface RegisterVehicleRepository {

    suspend fun registerVehicle(data: RegisterVehicleModel): Boolean

}