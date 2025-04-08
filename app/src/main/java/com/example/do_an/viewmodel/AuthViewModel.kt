package com.example.do_an.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login(callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000) // mô phỏng đăng nhập API
            // Kiểm tra mẫu email và password demo
            val isSuccess = _email.value == "test@gmail.com" && _password.value == "123456"
            callback(isSuccess)
            _isLoading.value = false
        }
    }
}
