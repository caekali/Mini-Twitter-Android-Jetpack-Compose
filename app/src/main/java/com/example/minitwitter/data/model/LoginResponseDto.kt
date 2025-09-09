package com.example.minitwitter.data.model


data class LoginResponseDto(
    val user: UserDto,
    val token: String
)
