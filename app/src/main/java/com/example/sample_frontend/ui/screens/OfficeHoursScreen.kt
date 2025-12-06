package com.example.sample_frontend.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sample_frontend.ui.components.Footer
import com.example.sample_frontend.ui.components.OfficeHourItem
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.viewmodel.CourseViewModel

@Composable
fun OfficeHoursScreen(
    id: Int?,
    navController: NavController,
    courseViewModel: CourseViewModel
) {
//    val course = id?.let { courseid ->
//        courseViewModel.courses.find { it.course.id == courseid }?.course
//    }

    val courseDetails by produceState<CourseResponse?>(initialValue = null, id) {
        id?.let { cid ->
            value = courseViewModel.getCourseById(cid)
        }
    }


    Scaffold(
        bottomBar = {Footer(navController = navController)}
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("home")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.border(
                        2.dp,
                        Color.Black,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "backwards arrow",
                        tint = Color.Black
                    )
                }

                if (courseDetails != null) {
                    OfficeHoursScreenHeader(courseDetails = courseDetails!!)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Instructors:", fontWeight = FontWeight.Bold)
                    courseDetails!!.instructors.forEach { instr ->
                        Text("- ${instr.name} (Contact: ${instr.netid})")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("TAs:", fontWeight = FontWeight.Bold)
                    courseDetails!!.tas.forEach { ta ->
                        Text("- ${ta.name} (Contact ${ta.netid})")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Office Hours:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Show office hours for this course
                    courseDetails!!.officeHours.forEach { oh ->
                        OfficeHourItem(officeHour = oh)
                    }
                } else {
                    Text("Course not found", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun OfficeHoursScreenHeader(
    courseDetails: CourseResponse
) {
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
            courseDetails?.let {
                Text(
                    "${courseDetails!!.code} - ${courseDetails!!.name}",
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
fun PreviewOfficerHoursScreen() {
    Scaffold(
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
//                Button(
//                    onClick = {
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = Color.Black
//                    ),
//                    modifier = Modifier.border(
//                        2.dp,
//                        Color.Black,
//                        shape = MaterialTheme.shapes.extraLarge
//                    )
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "backwards arrow",
//                        tint = Color.Black
//                    )
//                }
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
                        Text(
                                "MATH2210 - Linear Algebra",
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
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Instructors:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text("- Mr. Cao (Contact: cao70)")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("TAs:", fontWeight = FontWeight.Bold)
                    Text("- Greg Smith (Contact: gst437)")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Office Hours:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFE7F0F2),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tuesday: 10:00 - 11:00",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF197278)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "TA: Greg Smith",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFE7F0F2),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Thursday: 15:00 - 16:00",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF197278)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "TA: Greg Smith",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}