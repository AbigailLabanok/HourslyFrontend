package com.example.sample_frontend.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseUser(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("netid")
    val netId: String,

    @SerialName("courses")
    val courses: List<UserCourse>? = null
)
