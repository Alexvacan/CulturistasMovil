package com.easypark.app.registerparking.domain.usecase

import com.easypark.app.shared.domain.model.ParkingModel
import com.easypark.app.shared.domain.repository.ParkingRepository

class RegisterParkingUseCase(
    private val repository: ParkingRepository
) {
    suspend fun invoke(model: ParkingModel): Boolean {
        return repository.registerParking(model)
    }
}