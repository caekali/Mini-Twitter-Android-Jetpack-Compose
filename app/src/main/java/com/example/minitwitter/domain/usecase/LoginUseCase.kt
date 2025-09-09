package com.example.minitwitter.domain.usecase

import com.example.minitwitter.domain.repository.UserRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String) =
        userRepository.login(email, password)

}