package com.example.youtube_clone.domain.dto

class AccessTokenDTO(
        private val token: String
) {
    fun getToken(): String {
        return token
    }
}