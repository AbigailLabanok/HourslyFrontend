package com.example.sample_frontend.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_frontend.ui.data.sampleTeachers
import com.example.sample_frontend.viewmodel.TeacherViewModel

@Composable
fun NavWrapper() {
    val navController = rememberNavController()
    val teacherViewModel = remember { TeacherViewModel() }

    NavHost(
        navController = navController,
        startDestination = "open"
    ) {
        composable("open") {
            OpeningScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                teacherViewModel = teacherViewModel
            )
        }

        composable("officehours/{id}") {
            val id = it.arguments?.getString("id")
            OfficeHoursScreen(id, navController = navController)
        }

        composable("user") {
            UserScreen(
                navController = navController,
                teachers = sampleTeachers,
                teacherViewModel = teacherViewModel
            )
        }
    }
}