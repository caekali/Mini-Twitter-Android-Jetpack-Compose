package com.example.minitwitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minitwitter.data.local.AuthPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean?> = authPreferences.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setLoggedIn(token: String) {
        viewModelScope.launch { authPreferences.saveToken(token) }
    }

    fun logout() {
        viewModelScope.launch { authPreferences.clearToken() }
    }
}
