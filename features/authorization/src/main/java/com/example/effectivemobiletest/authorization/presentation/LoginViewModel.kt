package com.example.effectivemobiletest.authorization.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.effectivemobiletest.authorization.data.LoginState
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData(LoginState())
    val state: LiveData<LoginState> = _state

    fun onEmailChanged(email: String) {
        val currentState = _state.value ?: LoginState()
        val updatedState = currentState.copy(email = email)

        validateForm(updatedState)
    }

    fun onPasswordChanged(password: String) {
        val currentState = _state.value ?: LoginState()
        val updatedState = currentState.copy(password = password)

        validateForm(updatedState)
    }

    private fun validateForm(loginState: LoginState) {
        val isLoginEnabled = isEmailValid(loginState.email) && isPasswordValid(loginState.password)

        _state.value = loginState.copy(
            isLoginEnabled = isLoginEnabled
        )
    }

    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}