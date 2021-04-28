package com.example.youtube_clone.domain.dto

class UserDTO(
    private val email: String,
    private val nickname: String,
    private val profile: String?
) {
    fun getEmail(): String {
        return email
    }

    fun getNickname(): String {
        return nickname
    }

    fun getProfile(): String? {
        return profile
    }
}
