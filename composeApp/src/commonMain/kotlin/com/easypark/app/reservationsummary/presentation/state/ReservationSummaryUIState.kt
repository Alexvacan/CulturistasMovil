package com.easypark.app.reservationsummary.presentation.state

import com.easypark.app.reservationsummary.domain.model.ReservationSummaryModel

data class ReservationSummaryUiState(
    val isLoading: Boolean = false,
    val reservation: ReservationSummaryModel? = null,
    val error: String? = null
)
