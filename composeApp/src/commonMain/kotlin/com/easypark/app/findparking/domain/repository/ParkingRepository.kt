package com.easypark.app.findparking.domain.repository

import com.easypark.app.shared.domain.model.ParkingModel

interface ParkingRepository {
    suspend fun getAvailableParkings(): List<ParkingModel>
}