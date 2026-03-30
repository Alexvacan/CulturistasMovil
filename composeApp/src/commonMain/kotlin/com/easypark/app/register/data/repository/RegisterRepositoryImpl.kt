package com.easypark.app.register.data.repository

import com.easypark.app.register.domain.model.RegisterModel
import com.easypark.app.register.domain.repository.RegisterRepository

class RegisterRepositoryImpl : RegisterRepository {

    override suspend fun register(data: RegisterModel): Boolean {

        if (data.name.isBlank()) return false
        if (data.email.isBlank()) return false
        if (data.phone.isBlank()) return false
        if (data.password.length < 6) return false

        return true
    }
}