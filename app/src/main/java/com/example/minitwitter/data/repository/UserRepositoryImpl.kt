package com.example.minitwitter.data.repository

import com.example.minitwitter.data.mapper.toDomain
import com.example.minitwitter.data.model.ApiResponse
import com.example.minitwitter.data.remote.LoginRequest
import com.example.minitwitter.data.remote.UserApiService
import com.example.minitwitter.domain.model.AuthResult
import com.example.minitwitter.domain.model.User
import com.example.minitwitter.domain.repository.UserRepository

import retrofit2.HttpException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (
    private val api: UserApiService
) : UserRepository {

    override suspend fun login(email: String, password: String): AuthResult {
        return try {
            val response = api.login(LoginRequest(email, password))

            if (response.status == "success" && response.data != null) {
                AuthResult(
                    user = response.data.user.toDomain(),
                    token = response.data.token
                )
            } else {
                val validationErrors = response.errors?.joinToString(", ") { "${it.field}: ${it.message}" }
                throw Exception(validationErrors ?: response.message ?: "Unknown error")
            }

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val type = object : TypeToken<ApiResponse<Unit>>() {}.type
            val apiError: ApiResponse<Unit>? = Gson().fromJson(errorBody, type)

            val validationErrors = apiError?.errors
                ?.joinToString(", ") { "${it.field}: ${it.message}" }

            val finalMessage = validationErrors ?: apiError?.message ?: "Unexpected error"
            throw Exception(finalMessage)
        }
    }
}
