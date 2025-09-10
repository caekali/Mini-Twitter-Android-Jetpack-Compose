package com.example.minitwitter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.minitwitter.presentation.home.HomeScreenRoot
import com.example.minitwitter.presentation.signin.LoginScreenRoot
import com.example.minitwitter.presentation.signup.RegisterScreenRoot
import kotlinx.serialization.Serializable

@Serializable
data object LoginScreen : NavKey

@Serializable
data object HomeScreen : NavKey

@Serializable
data object RegisterScreen : NavKey

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(authViewModel: AuthViewModel) {
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    if (isLoggedIn == null) return
    val backStack = rememberNavBackStack(if (isLoggedIn == true) HomeScreen else LoginScreen)
    NavDisplay(
        backStack, entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ), entryProvider = { key ->
            when (key) {
                is LoginScreen -> NavEntry(key) {
                    LoginScreenRoot(onGoToRegister = {
                        backStack.addLast(RegisterScreen)
                    }, onLoginSuccess = {
//                        backStack.add(HomeScreen)
                        backStack.addLast(HomeScreen)
                    })
                }

                is RegisterScreen -> NavEntry(key) {
                    RegisterScreenRoot(onGoToLogin = {
                        backStack.add(LoginScreen)
                    }, onRegisterSuccess = {
                        backStack.add(HomeScreen)
                    })
                }

                is HomeScreen -> NavEntry(key) {
                    HomeScreenRoot()
                }

                else -> {
                    NavEntry(key) { Text(text = "Invalid Key: $it") }
                }
            }
        })
}