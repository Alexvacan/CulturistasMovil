package com.easypark.app.registervehicle.presentation.state

data class RegisterVehicleUIState(
    val plate: String = "",
    val model: String = "",
    val color: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)