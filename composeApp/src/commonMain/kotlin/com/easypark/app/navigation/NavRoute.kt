package com.easypark.app.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    
    @Serializable
    data object ParkingDetails: NavRoute()
    
    @Serializable
    data object BookingConfirmation: NavRoute()

    @Serializable
    data object Notifications: NavRoute()
    
    @Serializable
    data object Profile: NavRoute()

    @Serializable
    data object ProfileEdit: NavRoute()

    @Serializable
    object Github: NavRoute()

    @Serializable
    object Movies: NavRoute()
}
