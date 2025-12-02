package com.example.sample_frontend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TeacherCard(
    name: String,
    className: String,
    location: String,
    times: List<String>,
    isTeacher: Boolean,
    isFavorited: Boolean = false,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
){
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
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = className,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = location,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    times.forEach { time ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFE8F5FE)
                        ) {
                            Text(
                                text = "#$time",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF1DA1F2),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (isTeacher) {
                    Text(
                        text = "teacher",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray
                    )
                } else {
                    Text(
                        text = "teacher's assistant",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray
                    )
                }
            }

            IconButton(
                onClick = {
                    onFavoriteClick()
                }
            ) {
                Icon(
                    imageVector = if (isFavorited) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.Star
                    },
                    contentDescription = "favorite",
                    tint = if (isFavorited) {
                        Color(0xFF197278)
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTeacherCard() {
    TeacherCard(
        name = "sally richerdson",
        className = "linear algebra",
        location = "mallot hall",
        times = listOf("12pm-2pm: Tuesday","5pm-6pm: Thursday"),
        isTeacher = false,
        isFavorited = true,
        onClick = {},
        onFavoriteClick = {}
    )
}