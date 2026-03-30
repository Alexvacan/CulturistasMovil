package com.easypark.app.register.domain.usecase

import com.easypark.app.register.domain.model.RegisterModel
import com.easypark.app.register.domain.repository.RegisterRepository

class DoRegisterUseCase(
    private val repository: RegisterRepository
) {

    suspend operator fun invoke(data: RegisterModel): Boolean {
        return repository.register(data)
    }
}