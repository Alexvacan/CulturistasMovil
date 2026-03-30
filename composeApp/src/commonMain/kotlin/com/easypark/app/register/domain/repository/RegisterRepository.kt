package com.easypark.app.register.domain.repository

import com.easypark.app.register.domain.model.RegisterModel

interface RegisterRepository {

    suspend fun register(data: RegisterModel): Boolean

}