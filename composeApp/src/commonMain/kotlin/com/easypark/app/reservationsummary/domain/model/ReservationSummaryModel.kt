package com.easypark.app.reservationsummary.domain.model

data class ReservationSummaryModel(
    val id: String,
    val parkingName: String,
    val address: String,
    val assignedSpace: String,
    val entryTime: String,
    val estimatedDuration: String,
    val totalCost: Double,
    val paymentMethod: String
)
