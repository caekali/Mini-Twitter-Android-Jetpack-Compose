package com.example.minitwitter.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minitwitter.data.local.AuthPreferences
import com.example.minitwitter.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            is LoginUiEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            LoginUiEvent.Submit -> {
                login(uiState.value.email, uiState.value.password)
            }

            else -> Unit
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val authResult = loginUseCase.invoke(email, password)
                Log.d("LoginViewModel", "login: ${authResult}")
                authPreferences.saveToken(authResult.token)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message ?: "Something went wrong")
                Log.d("LoginViewModel", "login: ${e.message}")

            }
            _uiState.value = _uiState.value.copy(isLoading = true)

        }
    }

}