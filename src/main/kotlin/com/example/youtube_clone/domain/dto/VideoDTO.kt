package com.example.youtube_clone.domain.dto

import java.time.LocalDateTime

data class VideoDTO(
    private val videoId: Int,
    private val name: String,
    private val thumbnail: String,
    private val video: String,
    private val description: String,
    private val duration: Float,
    private val likes: Int,
    private val bads: Int,
    private val comments: Int,
    private val views: Int,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime
) {
}