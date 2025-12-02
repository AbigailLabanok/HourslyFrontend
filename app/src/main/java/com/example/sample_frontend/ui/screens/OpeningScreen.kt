package com.example.sample_frontend.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OpeningScreen(
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "hi there! welcome to the app :) click to proceed to learn about all of your TA's office hours!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {navController.navigate("home")}
        ) {
            Text("Enter",
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