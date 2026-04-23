package com.easypark.app.di

import com.easypark.app.notes.data.local.DatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformModule = module {
    single { DatabaseBuilder(androidContext()) }
    single { get<DatabaseBuilder>().createBuilder() }
}