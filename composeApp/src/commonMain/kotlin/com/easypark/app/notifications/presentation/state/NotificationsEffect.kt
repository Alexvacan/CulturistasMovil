package com.easypark.app.notifications.presentation.state

sealed interface NotificationsEffect {
    object NavigateBack : NotificationsEffect
}