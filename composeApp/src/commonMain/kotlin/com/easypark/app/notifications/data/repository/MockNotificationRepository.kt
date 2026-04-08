package com.easypark.app.notifications.data.repository

import com.easypark.app.notifications.domain.model.NotificationModel
import com.easypark.app.notifications.domain.repository.NotificationsRepository
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_calendar
import kotlinproject.composeapp.generated.resources.ic_notification

class MockNotificationsRepository : NotificationsRepository {
    override suspend fun getNotifications(): List<NotificationModel> {
        return listOf(
            NotificationModel("1", "Reserva confirmada", "Tu plaza A-24 está lista", "Hace 5 min", Res.drawable.ic_notification, true),
            NotificationModel("2", "Pago procesado", "Recibo #9821 enviado", "Hace 1 h", Res.drawable.ic_notification),
            NotificationModel("3", "Tu reserva expira pronto", "Quedan 15 minutos", "Hace 3 h", Res.drawable.ic_calendar),
            NotificationModel("4", "Ubicación guardada", "Nivel -2, Sector Verde.", "Ayer", Res.drawable.ic_notification)
        )
    }
}