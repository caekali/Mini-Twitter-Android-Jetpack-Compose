package com.example.minitwitter.presentation.signin

sealed class LoginUiEvent {
    data class EmailChanged(val email: String)  : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    data object Submit : LoginUiEvent()
    data object GoToRegister : LoginUiEvent()
}
