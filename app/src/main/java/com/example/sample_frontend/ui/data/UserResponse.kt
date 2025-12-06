package com.example.sample_frontend.ui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("netid")
    val netId: String,

    @SerialName("courses")
    val courses: List<UserCourse> = emptyList(),

    @SerialName("saved_office_hours")
    val savedOfficeHours: List<SavedOfficeHour> = emptyList()
)
