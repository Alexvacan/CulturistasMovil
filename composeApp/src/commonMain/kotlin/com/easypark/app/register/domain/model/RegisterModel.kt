package com.easypark.app.register.domain.model

data class RegisterModel(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val role: String
)