package com.easypark.app.di

import com.easypark.app.notifications.presentation.viewmodel.NotificationsViewModel
import com.easypark.app.register.presentation.viewmodel.RegisterViewModel
import com.easypark.app.signin.presentation.viewmodel.SignInViewModel
import com.easypark.app.spacemanagement.presentation.viewmodel.SpaceManagementViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::SpaceManagementViewModel)
    viewModelOf(::NotificationsViewModel)
}