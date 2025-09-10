package com.example.minitwitter.presentation.signup

sealed class RegisterUiEvent {
    data class NameChanged(val name: String)  : RegisterUiEvent()
    data class EmailChanged(val email: String)  : RegisterUiEvent()
    data class PasswordChanged(val password: String) : RegisterUiEvent()
    data object Submit : RegisterUiEvent()
    data object GoToLogin : RegisterUiEvent()
}
