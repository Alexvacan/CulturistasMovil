package com.easypark.app.di

import com.easypark.app.register.data.repository.RegisterRepositoryImpl
import com.easypark.app.register.domain.repository.RegisterRepository
import org.koin.dsl.module
import com.easypark.app.registerparking.data.MockParkingRepository
import com.easypark.app.registerparking.domain.repository.ParkingRepository
import com.easypark.app.signin.data.repository.AuthenticationRepositoryImpl
import com.easypark.app.signin.domain.repository.AuthenticationRepository
import com.easypark.app.spacemanagement.data.SpaceManagementMockRepository
import com.easypark.app.spacemanagement.domain.repository.SpaceManagementRepository

val dataModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl() }
    single<RegisterRepository> { RegisterRepositoryImpl() }
    single<ParkingRepository> { MockParkingRepository() }
    single<SpaceManagementRepository> { SpaceManagementMockRepository() }
}