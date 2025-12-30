package dev.espera.devtask.features.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.espera.devtask.R
import dev.espera.devtask.features.auth.viewmodels.AuthUiState
import dev.espera.devtask.features.auth.viewmodels.AuthViewModel
import dev.espera.devtask.ui.components.CustomTabSwitch
import dev.espera.devtask.ui.components.DevTaskButton
import dev.espera.devtask.ui.components.DevTaskInput
import dev.espera.devtask.ui.theme.BrightBlue
import dev.espera.devtask.ui.theme.DevTaskTheme
import dev.espera.devtask.ui.theme.Gray
import org.koin.androidx.compose.koinViewModel

data object AuthScreen

@Composable
fun AuthScreen(
    onLogInSuccessful: () -> Unit = {},
    viewModel: AuthViewModel = koinViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AuthScreenContent(
        uiState = uiState,
        onUserLogIn = viewModel::logIn,
        snackBarHostState = snackBarHostState,
    )

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val currentOnLoginSuccessful by rememberUpdatedState(onLogInSuccessful)
    LaunchedEffect(viewModel, lifecycle) {
        viewModel.uiState.collect {
            if (it.isUserLoggedIn) {
                currentOnLoginSuccessful()
            }

            if (it.errorMessage != null) {
                snackBarHostState.showSnackbar(it.errorMessage)
                viewModel.onErrorMessageShown()
            }
        }
    }
}

@Composable
private fun AuthScreenContent(
    uiState: AuthUiState,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    onUserLogIn: (email: String, password: String) -> Unit,
) {

    val tabs = listOf("Log In", "Sign Up")
    var currentTab by remember { mutableStateOf(0) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val canLogIn = email.isNotBlank() && password.isNotBlank() && !uiState.isProcessing

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .background(BrightBlue.copy(.5f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_terminal),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "DevTask", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Manage tasks. Automate breakdown.",
                style = MaterialTheme.typography.bodyMedium.copy(Gray)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
            ) {
                CustomTabSwitch(
                    selectedTabIndex = currentTab,
                    tabs = tabs,
                    onTabSelected = { tab -> currentTab = tab },
                )
                if (currentTab == 1) {
                    Spacer(modifier = Modifier.height(20.dp))
                    DevTaskInput(
                        label = "Name",
                        value = name,
                        placeholder = "Dev User",
                        leadingIcon = Icons.Default.Person,
                        onValueChange = { name = it },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DevTaskInput(
                    label = "Email Address",
                    value = email,
                    leadingIcon = Icons.Default.Email,
                    onValueChange = { email = it },
                    placeholder = "developer@example.com"
                )
                Spacer(modifier = Modifier.height(20.dp))
                DevTaskInput(
                    label = "Password",
                    value = password,
                    isPassword = true,
                    leadingIcon = Icons.Default.Lock,
                    onValueChange = { password = it },
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        "Forgot Password?", style = MaterialTheme.typography.bodyMedium.copy(
                            color = BrightBlue,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DevTaskButton(
                    text = "Log In",
                    isEnabled = canLogIn,
                    onClick = {
                        onUserLogIn(email, password)
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(.7f),
                    ) {
                        TermsAndConditionsFooter()
                    }
                }
            }
        }
    }
}

@Composable
fun TermsAndConditionsFooter() {
    val interactionListener = remember<LinkInteractionListener> {
        LinkInteractionListener { link ->
            if (link is LinkAnnotation.Clickable) {
                when (link.tag) {
                    "TOS" -> {
                        // Open TOS link
                    }

                    "PP" -> {
                        // Open PP link
                    }
                }
            }
        }
    }

    val annotatedString = remember(interactionListener) {
        buildAnnotatedString {
            append("By continuing, you agree to DevTask's ")
            pushLink(
                LinkAnnotation.Clickable(
                    tag = "TOS",
                    linkInteractionListener = interactionListener
                )
            )
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Terms of Service")
            }
            pop()
            append(" and ")
            pushLink(
                LinkAnnotation.Clickable(
                    tag = "PP",
                    linkInteractionListener = interactionListener
                )
            )
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Privacy Policy")
            }
            pop()
            append(".")
        }
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall.copy(color = Gray, textAlign = TextAlign.Center)
    )
}

@Preview
@Composable
fun AuthScreenPreview() {
    val mockSnackBarHostState = remember { SnackbarHostState() }

    DevTaskTheme {
        AuthScreenContent(
            uiState = AuthUiState(),
            onUserLogIn = { _, _ -> },
            snackBarHostState = mockSnackBarHostState,
        )
    }
}
