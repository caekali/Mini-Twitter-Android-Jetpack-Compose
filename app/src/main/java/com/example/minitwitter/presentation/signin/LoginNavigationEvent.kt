package com.example.minitwitter.presentation.signin

import com.example.minitwitter.base.UiEffect

sealed interface LoginNavigationEvent : UiEffect {
    data object NavigateToRegister : LoginNavigationEvent
    data object NavigateToHome : LoginNavigationEvent
}
