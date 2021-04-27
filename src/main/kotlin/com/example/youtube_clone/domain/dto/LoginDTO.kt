package com.example.youtube_clone.domain.dto

data class LoginDTO(
        private val email: String,
        private val password: String
) {
    fun getEmail(): String {
        return this.email
    }

    fun getPassword(): String {
        return this.password
    }
}