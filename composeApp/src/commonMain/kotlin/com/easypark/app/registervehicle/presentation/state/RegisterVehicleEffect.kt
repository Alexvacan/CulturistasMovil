package com.easypark.app.registervehicle.presentation.state

sealed interface RegisterVehicleEffect {
    data object NavigateBack : RegisterVehicleEffect
    data object NavigateNext : RegisterVehicleEffect
    data class ShowError(val message: String) : RegisterVehicleEffect
}