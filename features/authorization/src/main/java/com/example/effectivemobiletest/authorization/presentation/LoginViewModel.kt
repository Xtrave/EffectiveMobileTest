package com.example.effectivemobiletest.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData(false)
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _navigateToMain = MutableLiveData(false)
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    fun onEmailChanged(text: String) {
        _email.value = text
        validateForm()
    }

    fun onPasswordChanged(text: String) {
        _password.value = text
        validateForm()
    }

    private fun validateForm() {
        val email = _email.value ?: ""
        val password = _password.value ?: ""

        val isEmailValid = isValidEmail(email)
        val isPasswordValid = password.isNotBlank()

        _isLoginEnabled.value = isEmailValid && isPasswordValid
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isBlank()) return false

        val cyrillicPattern = Pattern.compile("[а-яА-Я]")
        if (cyrillicPattern.matcher(email).find()) return false

        val emailPattern = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )
        return emailPattern.matcher(email).matches()
    }

    fun onLoginClick() {
        if (_isLoginEnabled.value == true) {
            _navigateToMain.value = true
            _navigateToMain.value = false
        }
    }
}