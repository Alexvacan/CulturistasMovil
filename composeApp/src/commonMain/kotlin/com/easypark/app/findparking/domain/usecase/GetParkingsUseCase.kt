package com.easypark.app.findparking.domain.usecase

import com.easypark.app.findparking.domain.repository.ParkingRepository
import com.easypark.app.shared.domain.model.ParkingModel

class GetParkingsUseCase(private val repository: ParkingRepository) {
    suspend operator fun invoke(): List<ParkingModel> = repository.getAvailableParkings()
}