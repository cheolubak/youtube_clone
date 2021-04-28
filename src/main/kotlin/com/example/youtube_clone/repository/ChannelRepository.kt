package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.Channel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChannelRepository : JpaRepository<Channel, Int> {
}