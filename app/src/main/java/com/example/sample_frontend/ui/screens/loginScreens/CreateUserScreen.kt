package com.example.sample_frontend.ui.screens.loginScreens

import androidx.compose.foundation.BorderStroke
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
import com.example.sample_frontend.ui.data.User
import com.example.sample_frontend.viewmodel.UserUiState
import com.example.sample_frontend.viewmodel.UserViewModel
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.navigation.NavController


@Composable
fun CreateUserScreen(
    userViewModel: UserViewModel,
    navController: NavController
) {
    val uiState by userViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.users.size) {
        if (uiState.users.isNotEmpty()) {
            navController.navigate("home") {
                popUpTo("create") { inclusive = true }
            }
        }
    }

    CreateUserScreenContent(
        uiState = uiState,
        onCreateUser = userViewModel::createUser
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateUserScreenContent(
    uiState: UserUiState,
    onCreateUser: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome!", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        var name by remember { mutableStateOf("") }
        var netId by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF197278),
                unfocusedBorderColor = Color(0xFFACDDE7),
                focusedLabelColor = Color(0xFF197278)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = netId,
            onValueChange = { netId = it },
            label = { Text("NetID") },
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
            onClick = {
                onCreateUser(name, netId)
                println("CreateUser clicked: name=$name netid=$netId")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE7F0F2),
                contentColor = Color.Black
            ),
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFF197278)
            ),
        ) {
            Text("Create User")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewCreateUserScreen() {
    CreateUserScreenContent(
        uiState = UserUiState(
            isLoading = false,
            users = listOf(User("1", "Preview User", "abc123")),
            error = null
        ),
        onCreateUser = { _, _ -> }
    )
}
