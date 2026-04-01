package com.easypark.app.registervehicle.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easypark.app.registervehicle.presentation.state.*
import com.easypark.app.registervehicle.presentation.viewmodel.RegisterVehicleViewModel
import kotlinx.coroutines.flow.collectLatest

// 🔹 IMPORTS SHARED (ajusta paquetes según tu proyecto)
import com.easypark.app.shared.ParkHeader
import com.easypark.app.shared.ParkButton
import com.easypark.app.shared.ParkTextField

import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.car_image // 👈 debes agregar

@Composable
fun RegisterVehicleScreen(
    viewModel: RegisterVehicleViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                RegisterVehicleEffect.NavigateBack -> onBack()
                RegisterVehicleEffect.NavigateNext -> onNext()
                is RegisterVehicleEffect.ShowError -> println(effect.message)
            }
        }
    }

    Scaffold(
        topBar = {
            ParkHeader(
                title = "Registra tu vehiculo",
                onBackClick = {
                    viewModel.onEvent(RegisterVehicleEvent.OnBackClick)
                },
                onNotificationClick = null
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                ParkButton(
                    text = "Finalizar",
                    onClick = {
                        viewModel.onEvent(RegisterVehicleEvent.OnSubmitClick)
                    }
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // 🔹 IMAGEN
            Image(
                painter = painterResource(Res.drawable.car_image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Vehicle Details", style = MaterialTheme.typography.titleLarge)
            Text("Please provide your vehicle information to get started.")

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 INPUTS (usar shared)
            ParkTextField(
                value = state.plate,
                onValueChange = {
                    viewModel.onEvent(RegisterVehicleEvent.OnPlateChange(it))
                },
                placeholder = "e.g. ABC-1234"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ParkTextField(
                value = state.model,
                onValueChange = {
                    viewModel.onEvent(RegisterVehicleEvent.OnModelChange(it))
                },
                placeholder = "e.g. Toyota Camry 2024"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ParkTextField(
                value = state.color,
                onValueChange = {
                    viewModel.onEvent(RegisterVehicleEvent.OnColorChange(it))
                },
                placeholder = "Select vehicle color"
            )
        }
    }
}