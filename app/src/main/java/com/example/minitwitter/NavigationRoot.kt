package com.example.minitwitter

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.minitwitter.presentation.signin.LoginScreenRoot
import kotlinx.serialization.Serializable

@Serializable
data object LoginScreen : NavKey
@Serializable
data object HomeScreen : NavKey

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(LoginScreen)
    NavDisplay(
        backStack, entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ), entryProvider = { key ->
            when (key) {
                is LoginScreen -> NavEntry(key) {
                    LoginScreenRoot(onGoToRegister = {
                        backStack.add(HomeScreen)
                    }, onLoginSuccess = {
                        backStack.add(HomeScreen)
                    })
                }

                is HomeScreen -> NavEntry(key) {

                }

                else -> {
                    NavEntry(key) { Text(text = "Invalid Key: $it") }
                }
            }
        }
    )
}