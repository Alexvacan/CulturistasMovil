package com.easypark.app.findparking.domain.usecase

import com.easypark.app.shared.domain.model.ParkingModel
import com.easypark.app.shared.domain.repository.ParkingRepository

class GetParkingsUseCase(private val repository: ParkingRepository) {
    suspend operator fun invoke(): List<ParkingModel> = repository.getAvailableParkings()
}