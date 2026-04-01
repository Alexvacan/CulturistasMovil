package com.easypark.app.registerparking.data.repository

import com.easypark.app.registerparking.domain.repository.ParkingRepository
import com.easypark.app.shared.domain.model.ParkingModel

class OwnerParkingRepositoryImpl : ParkingRepository {
    override suspend fun registerParking(parking: ParkingModel): Boolean {
        println("Simulando guardado en BD: $parking")
        return true
    }
}