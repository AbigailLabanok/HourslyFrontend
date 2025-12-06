package com.example.sample_frontend.model
import androidx.compose.ui.graphics.vector.Path
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.ui.data.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class CreateUserRequest(
    val name: String,
    val netid: String
)

interface ApiService {
    @GET("/api/courses/")
    suspend fun getCourses(): CourseResponse

    @POST("/api/users/")
    suspend fun createUser(@Body request: CreateUserRequest): UserResponse

    @GET("/api/users/{user_id}")
    suspend fun getUser(@Path("user_id") userid: Int): UserResponse
}