package com.easypark.app.di

import com.easypark.app.spacemanagement.presentation.viewmodel.SpaceManagementViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::SpaceManagementViewModel)
}