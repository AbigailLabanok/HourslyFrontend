package com.example.sample_frontend.ui.data

data class TeacherInfo(
    val id: String,
    val name: String,
    val className: String,
    val location: String,
    val times: List<String>,
    val isTeacher: Boolean,
    var isFavorited: Boolean = false,
)