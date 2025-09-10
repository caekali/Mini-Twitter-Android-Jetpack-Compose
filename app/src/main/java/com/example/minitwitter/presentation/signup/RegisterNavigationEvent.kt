package com.example.minitwitter.presentation.signup

import com.example.minitwitter.base.UiEffect

sealed interface RegisterNavigationEvent : UiEffect {
    data object NavigateToLogin : RegisterNavigationEvent
    data object NavigateToHome : RegisterNavigationEvent
}
