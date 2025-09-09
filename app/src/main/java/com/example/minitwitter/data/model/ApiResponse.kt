package com.example.minitwitter.data.model

data class ApiResponse<T>(
    val status: String,
    val data: T?,
    val message: String?,
    val errors: List<ApiError>?,
    val pagination: Pagination?,
    val requestId: String?,
    val timestamp: String?
)

data class ApiError(
    val code: String,
    val field: String,
    val message: String
)

data class Pagination(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)
