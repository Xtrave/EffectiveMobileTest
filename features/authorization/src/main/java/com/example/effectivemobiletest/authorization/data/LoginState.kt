package com.example.effectivemobiletest.authorization.data

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false
)