package com.example.sample_frontend.model.repositories

import android.util.Log
import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.model.CreateUserRequest
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.ui.data.UserCourse
import com.example.sample_frontend.ui.data.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCourses(): Result<List<CourseResponse>> = runCatching {
        apiService.getCourses().courses
    }

    suspend fun getCourse(courseid: Int): Result<CourseResponse> = runCatching {
        apiService.getCourse(courseid)
    }
}