package com.example.sample_frontend.ui.data


import com.example.sample_frontend.ui.data.Course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoursesWrapper(
    val success: Boolean,
    val courses: List<CourseResponse>
)

@Serializable
data class CourseResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("code")
    val code: String,

    @SerialName("name")
    val name: String,

    @SerialName("students")
    val students: List<CourseUser> = emptyList(),

    @SerialName("instructors")
    val instructors: List<CourseUser> = emptyList(),

    @SerialName("tas")
    val tas: List<CourseUser> = emptyList(),

    @SerialName("office_hours")
    val officeHours: List<CourseOfficeHour> = emptyList()
)


