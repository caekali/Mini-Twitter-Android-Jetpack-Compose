package com.example.minitwitter.domain.repository

import com.example.minitwitter.domain.model.AuthResult
import com.example.minitwitter.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun register(name:String,email: String, password: String): AuthResult
}