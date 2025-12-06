package com.example.sample_frontend.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCourse(
    @SerialName("id")
    val id: Int,

    @SerialName("code")
    val code: String,

    @SerialName("name")
    val name: String,

    @SerialName("students")
    val students: List<CourseUser>? = null,

    @SerialName("instructors")
    val instructors: List<CourseUser>? = null,

    @SerialName("tas")
    val tas: List<CourseUser>? = null,

    @SerialName("office_hours")
    val officeHours: List<CourseOfficeHour>? = null
)

