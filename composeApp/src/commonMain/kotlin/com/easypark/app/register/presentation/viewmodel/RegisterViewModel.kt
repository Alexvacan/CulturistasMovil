package com.easypark.app.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easypark.app.register.domain.model.RegisterModel
import com.easypark.app.register.domain.usecase.DoRegisterUseCase
import com.easypark.app.register.presentation.state.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: DoRegisterUseCase
) : ViewModel(){
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

        val model = RegisterModel(
            name = current.name,
            email = current.email,
            phone = current.phone,
            password = current.password,
            role = current.role
        )

        viewModelScope.launch {

            val result = useCase(model)

            if (result) {
                _effect.send(RegisterEffect.NavigateToNext)
            } else {
                _effect.send(RegisterEffect.ShowError("Datos inválidos"))
            }
        }
    }
}