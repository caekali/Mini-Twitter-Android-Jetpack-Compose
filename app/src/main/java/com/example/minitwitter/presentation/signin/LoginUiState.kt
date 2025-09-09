package com.example.minitwitter.presentation.signin

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error:String = "",
)
