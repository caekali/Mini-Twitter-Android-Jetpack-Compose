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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minitwitter.presentation.components.LabeledTextField
import com.example.minitwitter.presentation.components.PrimaryButton
import com.example.minitwitter.presentation.theme.MiniTwitterTheme

@Composable
fun LoginScreenRoot(viewModel: LoginViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    LoginScreen(
        uiState = uiState, onEvent = {
            when (it) {
                is LoginUiEvent.GoToRegister -> {}
                else -> Unit
            }
            viewModel.onEvent(it)
        })
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

        LabeledTextField(
            value = uiState.email, onValueChange = {
                onEvent(LoginUiEvent.EmailChanged(it))
            }, label = "Email"
        )
        Spacer(Modifier.height(16.dp))
        LabeledTextField(
            value = uiState.password, onValueChange = {
                onEvent(LoginUiEvent.PasswordChanged(it))
            }, label = "Password", isPassword = true
        )
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
        PrimaryButton(label = "Sign Up", onClick = {
            onEvent(LoginUiEvent.Submit)
        })
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