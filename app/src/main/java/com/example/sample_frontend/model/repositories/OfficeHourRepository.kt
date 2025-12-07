package com.example.sample_frontend.model.repositories

import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.model.SaveOfficeHourRequest
import com.example.sample_frontend.ui.data.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfficeHourRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun saveOfficeHour(userid: Int, officehourid: Int): Result<UserResponse> = runCatching {
        apiService.saveOfficeHour(userid = userid, SaveOfficeHourRequest(officehourid = officehourid))
    }

    suspend fun unsaveOfficeHour(userid: Int, officehourid: Int): Result<UserResponse> = runCatching {
        apiService.unsaveOfficeHour(userid = userid, SaveOfficeHourRequest(officehourid = officehourid))
    }
}