package com.easypark.app.register.presentation.state

sealed interface RegisterEvent {

    data class OnNameChange(val name: String) : RegisterEvent
    data class OnEmailChange(val email: String) : RegisterEvent
    data class OnPhoneChange(val phone: String) : RegisterEvent
    data class OnPasswordChange(val password: String) : RegisterEvent

    data class OnRoleSelected(val role: String) : RegisterEvent

    data object OnRegisterClick : RegisterEvent
    data object OnLoginClick : RegisterEvent
}