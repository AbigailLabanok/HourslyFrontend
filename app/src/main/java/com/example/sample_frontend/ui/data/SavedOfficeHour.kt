package com.example.sample_frontend.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SavedOfficeHour(
    @SerialName("id")
    val id: Int,

    @SerialName("day")
    val day: String,

    @SerialName("start_time")
    val startTime: String,

    @SerialName("end_time")
    val endTime: String,

    @SerialName("location")
    val location: String,

    @SerialName("course")
    val course: UserCourse,

    @SerialName("ta")
    val ta: OfficeHourTA
)
