package com.easypark.app.notifications.presentation.state

import com.easypark.app.notifications.domain.model.NotificationModel

data class NotificationsUiState(
    val list: List<NotificationModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
