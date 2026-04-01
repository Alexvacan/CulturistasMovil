package com.easypark.app.spacemanagement.presentation.state

sealed interface SpaceManagementEvent {
    object LoadData : SpaceManagementEvent
}