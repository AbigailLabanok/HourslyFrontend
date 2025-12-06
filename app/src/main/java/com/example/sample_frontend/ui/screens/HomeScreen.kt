package com.example.sample_frontend.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sample_frontend.ui.components.CourseCard
import com.example.sample_frontend.ui.components.Footer
import com.example.sample_frontend.ui.components.HomeHeader
import com.example.sample_frontend.ui.data.sampleTeachers
import com.example.sample_frontend.viewmodel.CourseUI
import com.example.sample_frontend.viewmodel.CourseUiState
import com.example.sample_frontend.viewmodel.CourseViewModel
import com.example.sample_frontend.viewmodel.TeacherViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    courseViewModel: CourseViewModel
) {
    val uiState by courseViewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by courseViewModel.searchQuery.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        searchQuery = searchQuery,
        onSearchQueryChange = courseViewModel::setSearchQuery,
        onCourseClick = { id ->
            navController.navigate("officehours/$id")
                        },
        onFavoriteClick = courseViewModel::onClickFavorite,
        courseViewModel = courseViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    uiState: CourseUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onCourseClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    courseViewModel: CourseViewModel
) {
    val filteredCourses by courseViewModel.filteredCourses.collectAsState()

    Scaffold(
        topBar = { HomeHeader(searchQuery, onSearchQueryChange) },
        bottomBar = { /* you can pass navController to Footer if needed */ }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                //uiState.isLoading -> LoadingContent()
                uiState.error != null -> ErrorContent(uiState.error)
                uiState.courses.isEmpty() -> EmptyContent()
                else -> CoursesContent(
                    courses = filteredCourses,
                    onCourseClick = onCourseClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
private fun CoursesContent(
    courses: List<CourseUI>,
    onCourseClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(courses) { course ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CourseCard(
                    CourseUI = course,
                    isFavorited = course.isFavorited,
                    onClick = { onCourseClick(course.course.id) },
                    onFavoriteClick = { onFavoriteClick(course.course.id) },
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No courses found")
    }
}

@Composable
private fun ErrorContent(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $error",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}

//@Composable
//private fun LoadingContent() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        CircularProgressIndicator()
//    }
//}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
//    HomeScreenContent(
//        uiState = CourseUiState(
//            isLoading = false,
//            courses = emptyList(),
//            error = null
//        ),
//        searchQuery = "",
//        onSearchQueryChange = {},
//        onCourseClick = {},
//        onFavoriteClick = {}
//    )
}
