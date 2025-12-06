package com.example.sample_frontend.model
import com.example.sample_frontend.ui.data.CourseResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/courses/")
    suspend fun getCourses(): CourseResponse
}