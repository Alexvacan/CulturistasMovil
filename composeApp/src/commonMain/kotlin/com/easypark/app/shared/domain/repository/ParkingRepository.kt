package com.easypark.app.shared.domain.repository

import com.easypark.app.parkingdetails.domain.model.ParkingDetail
import com.easypark.app.shared.domain.model.ParkingModel

interface ParkingRepository {
    // 1. Para el Mapa (Conductor)
    suspend fun getAvailableParkings(): List<ParkingModel>

    // 2. Para los Detalles (Conductor)
    suspend fun getParkingDetail(id: String): ParkingDetail

    // 3. Para el Registro (Dueño)
    suspend fun registerParking(parking: ParkingModel): Boolean
}