package com.easypark.app.notifications.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easypark.app.notifications.domain.model.NotificationModel
import com.easypark.app.notifications.presentation.state.NotificationsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_notification
import kotlinproject.composeapp.generated.resources.ic_calendar

class NotificationsViewModel : ViewModel() {

    private val _state = MutableStateFlow(NotificationsUiState())
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val mockList = listOf(
                NotificationModel(
                    id = "1",
                    title = "Reserva confirmada en Parking Central",
                    description = "Tu plaza A-24 está lista para el...",
                    time = "Hace 5 min",
                    icon = Res.drawable.ic_notification,
                    isUnread = true
                ),
                NotificationModel(
                    id = "2",
                    title = "Pago procesado para Plaza B12",
                    description = "Recibo #9821 enviado a tu correo.",
                    time = "Hace 1 h",
                    icon = Res.drawable.ic_notification
                ),
                NotificationModel(
                    id = "3",
                    title = "Tu reserva expira pronto",
                    description = "Quedan 15 minutos en Parking Zona...",
                    time = "Hace 3 h",
                    icon = Res.drawable.ic_calendar
                ),
                NotificationModel(
                    id = "4",
                    title = "Ubicación guardada",
                    description = "Nivel -2, Sector Verde, Columna 4.",
                    time = "Ayer",
                    icon = Res.drawable.ic_notification
                ),
                NotificationModel(
                    id = "5",
                    title = "Descuento fin de semana",
                    description = "20% off en reservas de más de 4...",
                    time = "Ayer",
                    icon = Res.drawable.ic_notification
                ),
                NotificationModel(
                    id = "6",
                    title = "Acceso automático activado",
                    description = "Matrícula 1234-ABC reconocida...",
                    time = "2d",
                    icon = Res.drawable.ic_notification
                )
            )
            _state.update {
                it.copy(list = mockList, isLoading = false)
            }
        }
    }
}
