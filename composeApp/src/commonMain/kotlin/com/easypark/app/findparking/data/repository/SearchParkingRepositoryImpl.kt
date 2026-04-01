package com.easypark.app.findparking.data.repository

import com.easypark.app.findparking.domain.repository.ParkingRepository
import com.easypark.app.shared.domain.model.ParkingModel

class SearchParkingRepositoryImpl : ParkingRepository {
    override suspend fun getAvailableParkings(): List<ParkingModel> {
        return listOf(
            ParkingModel(
                "1",
                "Estacionamiento Central Oquendo",
                "Calle Oquendo 123",
                3.0,
                true,
                -17.3935,
                -66.1570
            ),
            ParkingModel("2", "Parqueo Viedma", "Av. Aniceto Arce", 5.0, true, -17.3895, -66.1490),
            ParkingModel("3", "Garage Norte", "Av. America", 4.0, false, -17.3750, -66.1550)
        )
    }
}