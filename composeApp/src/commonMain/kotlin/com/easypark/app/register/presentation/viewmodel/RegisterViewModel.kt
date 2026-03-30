package com.easypark.app.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easypark.app.register.presentation.state.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegisterUIState())
    val state = _state.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {

            is RegisterEvent.OnNameChange -> {
                _state.update { it.copy(name = event.name) }
            }

            is RegisterEvent.OnEmailChange -> {
                _state.update { it.copy(email = event.email) }
            }

            is RegisterEvent.OnPhoneChange -> {
                _state.update { it.copy(phone = event.phone) }
            }

            is RegisterEvent.OnPasswordChange -> {
                _state.update { it.copy(password = event.password) }
            }

            is RegisterEvent.OnRoleSelected -> {
                _state.update { it.copy(role = event.role) }
            }

            RegisterEvent.OnRegisterClick -> register()

            RegisterEvent.OnLoginClick -> {
                viewModelScope.launch {
                    _effect.send(RegisterEffect.NavigateToLogin)
                }
            }
        }
    }

    private fun register() {
        val current = _state.value

        val isValid =
            current.name.isNotBlank() &&
                    current.email.isNotBlank() &&
                    current.phone.isNotBlank() &&
                    current.password.length >= 6

        viewModelScope.launch {
            if (isValid) {
                _effect.send(RegisterEffect.NavigateToNext)
            } else {
                _effect.send(RegisterEffect.ShowError("Datos inválidos"))
            }
        }
    }
}