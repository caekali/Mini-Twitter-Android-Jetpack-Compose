package com.example.minitwitter.presentation.signup

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
import com.example.minitwitter.presentation.components.AppButton
import com.example.minitwitter.presentation.components.AppPasswordField
import com.example.minitwitter.presentation.components.AppTextButton
import com.example.minitwitter.presentation.components.AppTextField
import com.example.minitwitter.presentation.theme.MiniTwitterTheme

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = hiltViewModel(),
    onGoToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                RegisterNavigationEvent.NavigateToLogin -> onGoToLogin()
                RegisterNavigationEvent.NavigateToHome -> onRegisterSuccess()
            }
        }
    }
    RegisterScreen(uiState = uiState, onEvent = viewModel::onEvent)
}


@Composable
fun RegisterScreen(
    uiState: RegisterUiState, onEvent: (RegisterUiEvent) -> Unit
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
            text = "Create Account",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Join the conversation",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = uiState.name,
            onValueChange = { onEvent(RegisterUiEvent.NameChanged(it)) },
            label = "Name",
            isError = uiState.nameError != null,
            errorText = uiState.nameError
        )

        AppTextField(
            value = uiState.email,
            onValueChange = { onEvent(RegisterUiEvent.EmailChanged(it)) },
            label = "Email",
            isError = uiState.emailError != null,
            errorText = uiState.emailError
        )
        Spacer(Modifier.height(8.dp))

        AppPasswordField(
            value = uiState.password,
            onValueChange = { onEvent(RegisterUiEvent.PasswordChanged(it)) },
            label = "Password",
            isError = uiState.passwordError != null,
            errorText = uiState.passwordError
        )

        Spacer(Modifier.height(8.dp))

        AppButton(
            text = "Sign Up",
            onClick = { onEvent(RegisterUiEvent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            loading = uiState.isLoading
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", color = MaterialTheme.colorScheme.onBackground)
            AppTextButton(text = "Sign in", onClick = {
                onEvent(RegisterUiEvent.GoToLogin)
            })
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
        RegisterScreen(
            uiState = RegisterUiState(), onEvent = {

            })
    }
}