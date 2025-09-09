package com.example.minitwitter.data.remote

import com.example.minitwitter.data.model.ApiResponse
import com.example.minitwitter.data.model.LoginResponseDto
import com.example.minitwitter.data.model.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)

interface UserApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponseDto>
}