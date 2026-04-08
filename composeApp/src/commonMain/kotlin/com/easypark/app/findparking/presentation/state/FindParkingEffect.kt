package com.easypark.app.findparking.presentation.state

sealed interface FindParkingEffect {
    data class NavigateToBooking(val parkingId: String) : FindParkingEffect
    data class NavigateToDetails(val parkingId: String) : FindParkingEffect
    data class MoveCamera(val lat: Double, val lng: Double) : FindParkingEffect
}