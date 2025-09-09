package com.example.minitwitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.minitwitter.presentation.signin.LoginScreenRoot
import com.example.minitwitter.presentation.theme.MiniTwitterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniTwitterTheme {
                LoginScreenRoot()
            }
        }
    }
}

