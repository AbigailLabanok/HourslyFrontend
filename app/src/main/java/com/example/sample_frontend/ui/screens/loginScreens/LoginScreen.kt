package com.example.sample_frontend.ui.screens.loginScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sample_frontend.viewmodel.UserUiState
import com.example.sample_frontend.viewmodel.UserViewModel
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.navigation.NavController


@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    navController: NavController
) {
    val uiState by userViewModel.uiState.collectAsState()
    val currentUser by userViewModel.currentUser.collectAsState()

    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LoginScreenContent(
        uiState = uiState,
        onLogin = userViewModel::fetchUser,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreenContent(
    uiState: UserUiState,
    onLogin: (Int) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome Back!", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        var userid by remember { mutableStateOf("") }

        OutlinedTextField(
            value = userid,
            onValueChange = { userid = it.filter { character -> character.isDigit() } },
            label = { Text("User Id") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF197278),
                unfocusedBorderColor = Color(0xFFACDDE7),
                focusedLabelColor = Color(0xFF197278)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = !uiState.isLoading,
            onClick = { onLogin(userid.toInt()) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE7F0F2),
                contentColor = Color.Black
            ),
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFF197278)
            ),
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Don't have an account? Create one now!", style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp,
            modifier = Modifier.clickable{
                navController.navigate("create")
            }
        )

    }
}

@Preview (showBackground = true)
@Composable
fun PreviewLoginScreen() {
//    LoginScreenContent(
//        uiState = UserUiState(
//            isLoading = false,
//            users = listOf(User("1", "Preview User", "abc123")),
//            error = null
//        ),
//        onLogin = { _-> }
//    )
}
