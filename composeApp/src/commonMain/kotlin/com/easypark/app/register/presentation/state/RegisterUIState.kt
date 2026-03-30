package com.easypark.app.register.presentation.state

data class RegisterUIState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val role: String = "CONDUCTOR",
    val isLoading: Boolean = false,
    val error: String? = null
)