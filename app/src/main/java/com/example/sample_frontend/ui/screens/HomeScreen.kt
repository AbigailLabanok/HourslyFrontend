package com.example.sample_frontend.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sample_frontend.ui.components.Footer
import com.example.sample_frontend.ui.components.HomeHeader
import com.example.sample_frontend.ui.components.TeacherCard
import com.example.sample_frontend.ui.data.sampleTeachers
import com.example.sample_frontend.viewmodel.TeacherViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    teacherViewModel: TeacherViewModel
) {
    Scaffold(
        topBar = { HomeHeader(searchQuery = "", onSearchQueryChange = {}) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(teacherViewModel.teachers) {
                    TeacherCard(
                        name = it.name,
                        className = it.className,
                        location = it.location,
                        times = it.times,
                        isTeacher = it.isTeacher,
                        isFavorited = it.isFavorited,
                        onClick = {
                            val id = it.id
                            navController.navigate("officehours/$id")
                        },
                        onFavoriteClick = { teacherViewModel.onClickFavorite(it.id) }
                   )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        teacherViewModel = remember { TeacherViewModel() }
    )
}
