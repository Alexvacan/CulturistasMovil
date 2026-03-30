package com.easypark.app.register.presentation.state

sealed interface RegisterEffect {
    data object NavigateToLogin : RegisterEffect
    data object NavigateToNext : RegisterEffect
    data class ShowError(val message: String) : RegisterEffect
}