package com.example.minitwitter.data.mapper

import com.example.minitwitter.data.model.UserDto
import com.example.minitwitter.domain.model.User

fun UserDto.toDomain() : User{
    return User(id,name,email)
}