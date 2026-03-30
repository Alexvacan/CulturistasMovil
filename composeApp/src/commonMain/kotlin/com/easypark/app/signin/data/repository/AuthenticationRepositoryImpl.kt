package com.easypark.app.signin.data.repository

import com.easypark.app.signin.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl : AuthenticationRepository {

    override suspend fun login(email: String, password: String): Boolean {

        return email == "test@test.com" && password == "123456"
    }
}