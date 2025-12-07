package com.example.sample_frontend.model.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.ui.data.CourseResponse
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

    suspend fun getUserCourses(userid: Int?): Result<List<CourseResponse>> = runCatching {
        val userResponse = apiService.getUser(userid)
        val savedOfficeHours = userResponse.savedOfficeHours.map { it.id }.toSet()

        apiService.getCourses().courses.map { course ->
            val updatedOH = course.officeHours.map { oh ->
                oh.apply { isSaved.value = savedOfficeHours.contains(oh.id) }
            }
            course.copy(officeHours = updatedOH)
        }
    }
}