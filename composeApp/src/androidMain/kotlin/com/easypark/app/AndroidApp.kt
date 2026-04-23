package com.easypark.app

import android.app.Application
import androidx.work.Configuration
import com.easypark.app.di.getModules
import com.easypark.app.di.platformModule
import com.easypark.app.notes.worker.CacheCleanupWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.dsl.module
import org.koin.androidx.workmanager.dsl.workerOf

class AndroidApp : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AndroidApp)
            val workerModule = module {
                workerOf(::CacheCleanupWorker)
            }
            modules(getModules(platformModule) + workerModule)
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory())
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}