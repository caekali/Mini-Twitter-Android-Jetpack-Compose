package com.example.minitwitter.presentation.signup


import androidx.lifecycle.viewModelScope
import com.example.minitwitter.AuthViewModel
import com.example.minitwitter.base.BaseViewModel
import com.example.minitwitter.domain.usecase.RegisterUseCase
import com.example.minitwitter.util.exceptions.ApiValidationException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase,
    private val authViewModel: AuthViewModel
) : BaseViewModel<RegisterNavigationEvent>() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            is RegisterUiEvent.NameChanged -> _uiState.update { it.copy(name = event.name) }
            is RegisterUiEvent.EmailChanged -> _uiState.update { it.copy(email = event.email) }
            is RegisterUiEvent.PasswordChanged -> _uiState.update { it.copy(password = event.password) }
            RegisterUiEvent.Submit -> {
                register(uiState.value.name, uiState.value.email, uiState.value.password)
            }

            RegisterUiEvent.GoToLogin -> sendEffect(RegisterNavigationEvent.NavigateToLogin)
        }
    }


    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val result = registerUseCase(name, email, password)
                authViewModel.setLoggedIn(result.token)
                sendEffect(RegisterNavigationEvent.NavigateToHome)
                _uiState.value = RegisterUiState(isLoading = false)
            } catch (e: ApiValidationException) {
                _uiState.value = RegisterUiState(
                    isLoading = false,
                    nameError = e.errors["name"],
                    emailError = e.errors["email"],
                    passwordError = e.errors["password"]
                )
            } catch (e: Exception) {
                _uiState.value = RegisterUiState(
                    isLoading = false,
                    generalError = e.message
                )
            }
        }
    }

}