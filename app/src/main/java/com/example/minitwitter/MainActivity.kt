package com.example.minitwitter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minitwitter.presentation.home.HomeScreen
import com.example.minitwitter.presentation.signin.LoginScreenRoot
import com.example.minitwitter.presentation.theme.MiniTwitterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val authViewModel: AuthViewModel by viewModels()

        splashscreen.setKeepOnScreenCondition {
            authViewModel.isLoggedIn.value == null
        }

        enableEdgeToEdge()
        setContent {
            MiniTwitterTheme {
                NavigationRoot(authViewModel = authViewModel)
            }
        }
    }
}




