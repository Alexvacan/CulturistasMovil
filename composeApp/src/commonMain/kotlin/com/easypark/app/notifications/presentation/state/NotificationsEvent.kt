package com.easypark.app.notifications.presentation.state

sealed interface NotificationsEvent {
    object LoadNotifications : NotificationsEvent
    object OnBackClick : NotificationsEvent
}