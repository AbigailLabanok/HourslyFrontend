package com.example.sample_frontend.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun Footer(
    navController: NavController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        NavigationBarItem( // built in composable --> represents the diff icons you can click at the bottom
            selected = (currentRoute == "home"), // represents when that item is clicked on
            onClick = {
                navController.navigate("home")
            },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "home button") },
            label = { Text(text = "Home") }
        )
        NavigationBarItem( // built in composable --> represents the diff icons you can click at the bottom
            selected = (currentRoute == "user"), // represents when that item is clicked on
            onClick = {
                navController.navigate("user")
            },
            icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user button") },
            label = { Text(text = "User") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFooter() {
    Footer(navController = rememberNavController())
}