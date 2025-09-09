package com.example.minitwitter.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.minitwitter.presentation.theme.MiniTwitterTheme

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = hiltViewModel(),
    onGoToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                LoginNavigationEvent.NavigateToRegister -> onGoToRegister()
                LoginNavigationEvent.NavigateToHome -> onLoginSuccess()
            }
        }
    }
    LoginScreen(uiState = uiState, onEvent = viewModel::onEvent)
}


@Composable
fun LoginScreen(
    uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Sign in to continue",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(24.dp))

//        LabeledTextField(
//            value = uiState.email, onValueChange = {
//                onEvent(LoginUiEvent.EmailChanged(it))
//            }, label = "Email"
//        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                onEvent(LoginUiEvent.EmailChanged(it))
            },
            label = { Text("Email") },
            isError = uiState.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (uiState.emailError != null) {
                    Text(
                        text = uiState.emailError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            })


        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
                onEvent(LoginUiEvent.PasswordChanged(it))
            },
            label = { Text("Password") },
            isError = uiState.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (uiState.passwordError != null) {
                    Text(
                        text = uiState.passwordError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            })

        Spacer(Modifier.height(16.dp))
//        LabeledTextField(
//            value = uiState.password, onValueChange = {
//                onEvent(LoginUiEvent.PasswordChanged(it))
//            }, label = "Password", isPassword = true
//        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {

            }) {
                Text("Forgot Password?")
            }
        }
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { onEvent(LoginUiEvent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            Text(if (uiState.isLoading) "Loading..." else "Sign Up")
        }

//        PrimaryButton(label = "Sign Up", onClick = {
//            onEvent(LoginUiEvent.Submit)
//        })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?", color = MaterialTheme.colorScheme.onBackground)
            TextButton(onClick = {

            }) {
                Text("Sign Up?")
            }
        }

        uiState.generalError?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    MiniTwitterTheme {
        LoginScreen(
            uiState = LoginUiState(), onEvent = {

            })
    }
}