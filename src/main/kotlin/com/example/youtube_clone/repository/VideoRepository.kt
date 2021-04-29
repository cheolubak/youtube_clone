package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.Channel
import com.example.youtube_clone.domain.entity.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, Int> {
  fun findByChannelOrderByCreatedAtDesc(channel: Channel)
}
