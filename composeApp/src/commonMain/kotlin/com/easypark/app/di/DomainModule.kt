package com.easypark.app.di

import org.koin.dsl.module
import com.easypark.app.register.domain.usecase.DoRegisterUseCase
import com.easypark.app.registerparking.domain.usecase.RegisterParkingUseCase
import com.easypark.app.signin.domain.usecase.DoLoginUseCase
import com.easypark.app.spacemanagement.domain.usecase.GetSpaceDataUseCase

val domainModule = module {
    factory { DoLoginUseCase(get()) }
    factory { DoRegisterUseCase(get()) }
    factory { RegisterParkingUseCase(get()) }
    factory { GetSpaceDataUseCase(get()) }
}