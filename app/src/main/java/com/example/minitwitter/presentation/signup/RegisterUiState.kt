package com.example.minitwitter.presentation.signup

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val emailError: String? = null,
    val nameError: String? = null,
    val passwordError: String? = null,
    val generalError: String? = null
)
