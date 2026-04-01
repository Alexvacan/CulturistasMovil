package com.easypark.app.registervehicle.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easypark.app.registervehicle.domain.model.RegisterVehicleModel
import com.easypark.app.registervehicle.domain.usecase.RegisterVehicleUseCase
import com.easypark.app.registervehicle.presentation.state.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterVehicleViewModel(
    private val useCase: RegisterVehicleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterVehicleUIState())
    val state = _state.asStateFlow()

    private val _effect = Channel<RegisterVehicleEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: RegisterVehicleEvent) {
        when (event) {

            is RegisterVehicleEvent.OnPlateChange -> {
                _state.update { it.copy(plate = event.plate) }
            }

            is RegisterVehicleEvent.OnModelChange -> {
                _state.update { it.copy(model = event.model) }
            }

            is RegisterVehicleEvent.OnColorChange -> {
                _state.update { it.copy(color = event.color) }
            }

            RegisterVehicleEvent.OnSubmitClick -> submit()

            RegisterVehicleEvent.OnBackClick -> {
                viewModelScope.launch {
                    _effect.send(RegisterVehicleEffect.NavigateBack)
                }
            }
        }
    }

    private fun submit() {
        val current = _state.value

        val model = RegisterVehicleModel(
            plate = current.plate,
            model = current.model,
            color = current.color
        )

        viewModelScope.launch {

            val result = useCase(model)

            if (result) {
                _effect.send(RegisterVehicleEffect.NavigateNext)
            } else {
                _effect.send(RegisterVehicleEffect.ShowError("Datos incompletos"))
            }
        }
    }
}