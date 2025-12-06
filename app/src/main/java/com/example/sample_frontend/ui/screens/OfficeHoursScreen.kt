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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.sample_frontend.ui.data.CourseOfficeHour
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.viewmodel.CourseUI
import com.example.sample_frontend.viewmodel.CourseViewModel
import kotlinx.serialization.json.Json

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
        topBar = { OfficeHoursScreenHeader(
            courseDetails = courseDetails,
            navController = navController,
            onFavoriteClick = {
                courseDetails?.id?.let { courseViewModel.onClickFavorite(it) }
            },
            CourseUI = courseViewModel.courses.firstOrNull { it.course.id == id } ?: CourseUI(courseDetails!!, false)
        ) },
        bottomBar = {Footer(navController = navController)}
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                if (courseDetails != null) {

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    Text("Instructors:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    courseDetails!!.instructors.forEach { instr ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${instr.name}",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(18.dp)
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${instr.netid}",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    Text("TAs:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    courseDetails!!.tas.forEach { ta ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${ta.name}",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(18.dp)
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${ta.netid}",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    Text("Office Hours:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    courseDetails!!.officeHours.forEach { oh ->
                        OfficeHours(officeHour = oh)
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
    courseDetails: CourseResponse?,
    navController: NavController,
    onFavoriteClick: (Int) -> Unit,
    CourseUI: CourseUI
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
        IconButton(
            onClick = { navController.navigate("home")}
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "leftarrow",
                tint =
                    Color(0xFF197278)

            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            courseDetails?.let {
                Text(
                    "${courseDetails.code} - ${courseDetails.name}",
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
        IconButton(
            onClick = {
                courseDetails?.id?.let { id ->
                    onFavoriteClick(id)
                }
            }
        ) {
            Icon(
                imageVector = if (CourseUI.isFavorited) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                },
                contentDescription = "favorite",
                tint = if (CourseUI.isFavorited) {
                    Color(0xFF197278)
                } else {
                    Color.Gray
                }
            )
        }
    }
}

@Composable
fun OfficeHours(officeHour: CourseOfficeHour){
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFE7F0F2),
        modifier = Modifier
            .wrapContentWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "${officeHour.day}: ${officeHour.startTime} - ${officeHour.endTime}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF197278),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "TA: ${officeHour.ta.name}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Location: ${officeHour.location}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
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
                    IconButton(
                        onClick = {
                            //onFavoriteClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "favorite",
                            tint =
                                Color(0xFF197278)

                        )
                    }
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
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "favorite",
                            tint =
                                Color(0xFF197278)
                        )
                    }
                }
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                    Text(
                        "Instructors:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        "• Mr. Cao (Contact: cao70)",
                        fontSize = 16.sp,

                    )

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                    Text(
                        "TAs:",
                        fontWeight = FontWeight.Bold,
                        fontSize =20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text("• Greg Smith (Contact: gst437)",
                        fontSize = 16.sp
                    )

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                    Text("Office Hours:",
                        fontWeight = FontWeight.Bold,
                        fontSize =  20.sp
                    )
                    //Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFE7F0F2),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Tuesday: 10:00 - 11:00",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF197278),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "TA: Greg Smith",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Location: Goldwin Smith Hall",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray,
                            fontSize = 16.sp
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
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Thursday: 15:00 - 16:00",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF197278),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "TA: Greg Smith",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Location: Mallot Hall",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}