package com.easypark.app.di

import com.easypark.app.notifications.data.repository.MockNotificationsRepository
import com.easypark.app.notifications.domain.repository.NotificationsRepository
import com.easypark.app.register.data.repository.RegisterRepositoryImpl
import com.easypark.app.register.domain.repository.RegisterRepository
import org.koin.dsl.module
import com.easypark.app.registerparking.data.repository.MockParkingRepository
import com.easypark.app.registerparking.domain.repository.ParkingRepository
import com.easypark.app.signin.data.repository.AuthenticationRepositoryImpl
import com.easypark.app.signin.domain.repository.AuthenticationRepository
import com.easypark.app.spacemanagement.data.repository.SpaceManagementMockRepository
import com.easypark.app.spacemanagement.domain.repository.SpaceManagementRepository

val dataModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl() }
    single<RegisterRepository> { RegisterRepositoryImpl() }
    single<ParkingRepository> { MockParkingRepository() }
    single<SpaceManagementRepository> { SpaceManagementMockRepository() }
    single<NotificationsRepository> { MockNotificationsRepository() }
}