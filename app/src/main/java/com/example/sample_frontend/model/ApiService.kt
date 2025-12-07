package com.example.sample_frontend.model
import com.example.sample_frontend.ui.data.CourseResponse
import com.example.sample_frontend.ui.data.CoursesWrapper
import com.example.sample_frontend.ui.data.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class CreateUserRequest(
    val name: String,
    val netid: String
)

data class SaveOfficeHourRequest(
    val officehourid: Int,
)

interface ApiService {
    @GET("/api/courses/")
    suspend fun getCourses(): CoursesWrapper

    @GET("/api/courses/{course_id}/")
    suspend fun getCourse(@Path("course_id") courseId: Int): CourseResponse

    @POST("/api/users/")
    suspend fun createUser(@Body request: CreateUserRequest): UserResponse

    @GET("/api/users/{user_id}/")
    suspend fun getUser(@Path("user_id") userid: Int?): UserResponse

    @POST("/api/users/{user_id}/save_officehour/")
    suspend fun saveOfficeHour(@Path("user_id") userid: Int, @Body request: SaveOfficeHourRequest): UserResponse

    @POST("/api/users/<int:user_id>/unsave_officehour/")
    suspend fun unsaveOfficeHour(@Path("user_id") userid: Int, @Body request: SaveOfficeHourRequest): UserResponse
}