package com.example.sample_frontend.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sample_frontend.ui.data.sampleTeachers
import com.example.sample_frontend.ui.screens.loginScreens.CreateUserScreen
import com.example.sample_frontend.ui.screens.loginScreens.LoginScreen
import com.example.sample_frontend.ui.screens.loginScreens.OpeningScreen
import com.example.sample_frontend.viewmodel.CourseViewModel
import com.example.sample_frontend.viewmodel.TeacherViewModel
import com.example.sample_frontend.viewmodel.UserViewModel

@Composable
fun NavWrapper(
    userViewModel: UserViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
    courseViewModel: CourseViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
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
                courseViewModel = courseViewModel
            )
        }

        composable(
            "officehours/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType})
        ) { backStackEntry ->
            val courseid = backStackEntry.arguments?.getInt("id")
            OfficeHoursScreen(
                id = courseid,
                navController = navController,
                courseViewModel = courseViewModel
            )
        }

        composable("user") {
            UserScreen(
                navController = navController,
                teachers = sampleTeachers,
                teacherViewModel = teacherViewModel,
                userViewModel = userViewModel
            )
        }

        composable("create") {
            CreateUserScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
    }
}