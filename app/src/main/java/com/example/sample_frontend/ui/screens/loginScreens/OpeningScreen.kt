package com.example.sample_frontend.ui.screens.loginScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OpeningScreen(
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(Color(0xFFACDDE7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to Hoursly",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(0f, 0f),
                    blurRadius = 6f
                )
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Icon(
            imageVector = Icons.Rounded.DateRange,
            contentDescription = "calendar icon",
            tint = Color(0xFF197278),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {navController.navigate("login")},
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFF197278)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE7F0F2),
                contentColor = Color.Black
            )
        ) {
            Text("Login",
                fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOpeningScreen() {
    OpeningScreen(
        navController = rememberNavController()
    )
}