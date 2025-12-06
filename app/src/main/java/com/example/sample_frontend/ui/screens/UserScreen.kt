package com.example.sample_frontend.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sample_frontend.ui.components.Footer
import com.example.sample_frontend.ui.data.TeacherInfo
import com.example.sample_frontend.ui.data.sampleTeachers
import com.example.sample_frontend.viewmodel.TeacherViewModel
import com.example.sample_frontend.viewmodel.UserViewModel

@Composable
fun UserScreen(
    navController: NavHostController,
    teachers: List<TeacherInfo>,
    teacherViewModel: TeacherViewModel,
    userViewModel: UserViewModel
) {

    val favoriteTeachers = teacherViewModel.teachers.filter { it.isFavorited }

    Scaffold(
        topBar = { UserScreenHeader(userViewModel = userViewModel) },
        bottomBar = { Footer(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (favoriteTeachers.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No favorites yet!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(favoriteTeachers) {
//                        TeacherCard(
//                            name = it.name,
//                            className = it.className,
//                            location = it.location,
//                            times = it.times,
//                            isTeacher = it.isTeacher,
//                            isFavorited = it.isFavorited,
//                            onClick = {
//                                val id = it.id
//                                navController.navigate("officehours/$id")
//                            },
//                            onFavoriteClick = {teacherViewModel.onClickFavorite(it.id)}
//                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserScreenHeader(
    userViewModel: UserViewModel

) {
    val currentUser by userViewModel.currentUser.collectAsState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(NavigationBarDefaults.containerColor)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "left arrow",
            tint = Color(0xFF197278)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            currentUser?.let {
                Text(
                    "Logged in as ${it.name} (ID: ${it.id})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )
            } ?: Text(
                "Loading user...",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "left arrow",
            tint = Color(0xFF197278)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreen() {
    val sampleTeachers = sampleTeachers

    //UserScreen(navController = rememberNavController(), teachers = sampleTeachers, teacherViewModel = remember { TeacherViewModel() })
}