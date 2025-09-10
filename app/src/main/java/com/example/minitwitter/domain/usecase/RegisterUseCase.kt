package com.example.minitwitter.domain.usecase

import com.example.minitwitter.domain.repository.UserRepository
import jakarta.inject.Inject

class RegisterUseCase @Inject constructor(val userRepository: UserRepository) {
    suspend operator fun invoke(name:String,email: String, password: String) =
        userRepository.register(name,email, password)

}