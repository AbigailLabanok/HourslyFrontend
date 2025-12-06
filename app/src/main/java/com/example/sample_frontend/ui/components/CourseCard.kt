package com.example.sample_frontend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sample_frontend.model.repositories.CourseRepository
import com.example.sample_frontend.ui.data.Course
import com.example.sample_frontend.ui.data.CourseOfficeHour
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.ui.data.OfficeHourTA
import com.example.sample_frontend.viewmodel.CourseUI

@Composable
fun CourseCard(
    CourseUI: CourseUI,
    isFavorited: Boolean = false,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val course = CourseUI.course

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(16.dp)
            ) {
                Text(
                    text = "${course.name} (${course.code})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (!course.instructors.isNullOrEmpty()) {
                    Text(
                        text = "Instructors: ${course.instructors.joinToString { it.name }}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (!course.tas.isNullOrEmpty()) {
                    Text(
                        text = "Teacher Assistants: ${course.tas.joinToString { it.name }}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (!course.officeHours.isNullOrEmpty()) {
                    Text(
                        text = "Upcoming Office Hours:",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        course.officeHours.firstOrNull()?.let { officeHour ->
                            OfficeHourItem(officeHour)
                        }
                    }
                }
            }

            IconButton(
                onClick = {
                    onFavoriteClick()
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
}

@Composable
fun OfficeHourItem(
    officeHour: CourseOfficeHour
) {
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
                text = "${officeHour.day}: ${officeHour.startTime} - ${officeHour.endTime}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF197278)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "TA: ${officeHour.ta.name}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseCard() {
//    val sampleCourse = CourseResponse(
//        id = 1,
//        code = "MATH101",
//        name = "Linear Algebra",
//        students = emptyList(),
//        instructors = listOf(
//            com.example.sample_frontend.ui.data.CourseUser(1, "Prof. Alice", "aa123")
//        ),
//        tas = listOf(
//            com.example.sample_frontend.ui.data.CourseUser(2, "Jack Smith", "js456")
//        ),
//        officeHours = listOf(
//            CourseOfficeHour(
//                id = 1,
//                day = "Tuesday",
//                startTime = "3:00pm",
//                endTime = "5:00pm",
//                location = "Mallot Hall",
//                ta = com.example.sample_frontend.ui.data.OfficeHourTA(
//                    id = 2,
//                    name = "Jack Smith",
//                    netId = "js456"
//                )
//            )
//        )
//    )
//
//    CourseCard(
//        course = sampleCourse,
//        isFavorited = true,
//        onClick = {},
//        onFavoriteClick = {}
//    )
}
