package com.example.minitwitter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : UiEffect> : ViewModel() {
    private val _effect = MutableSharedFlow<E>()
    val effect = _effect.asSharedFlow()

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}

interface UiEffect