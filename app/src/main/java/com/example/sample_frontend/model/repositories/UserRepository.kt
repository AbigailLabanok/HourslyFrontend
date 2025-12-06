package com.example.sample_frontend.model.repositories

import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.model.CreateUserRequest
import com.example.sample_frontend.ui.data.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun createUser(name: String, netid: String): Result<UserResponse> = runCatching {
        apiService.createUser(
            CreateUserRequest(name = name, netid = netid)
        )
    }

    suspend fun getUser(userid: Int): Result<UserResponse> = runCatching {
        apiService.getUser(
            userid
        )
    }
}