package com.easypark.app.reservationsummary.domain.usecase

import com.easypark.app.reservationsummary.domain.model.ReservationSummaryModel
import com.easypark.app.shared.domain.repository.ParkingRepository

class GetReservationSummaryUseCase(
    private val repository: ParkingRepository
) {
    suspend operator fun invoke(reservationId: String): ReservationSummaryModel {
        return repository.getReservationSummary(reservationId)
    }
}
