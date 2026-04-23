package com.easypark.app.di

import org.koin.core.module.Module

fun getModules(platformModule: Module) = listOf(
    domainModule,
    presentationModule,
    dataModule,
    platformModule
)