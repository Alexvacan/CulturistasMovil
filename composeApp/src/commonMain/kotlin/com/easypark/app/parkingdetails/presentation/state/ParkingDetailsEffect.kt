package com.easypark.app.parkingdetails.presentation.state

sealed interface ParkingDetailsEffect {
    data object NavigateBack : ParkingDetailsEffect
    data class NavigateToBooking(val id: String) : ParkingDetailsEffect
    data class ShowError(val message: String) : ParkingDetailsEffect
}