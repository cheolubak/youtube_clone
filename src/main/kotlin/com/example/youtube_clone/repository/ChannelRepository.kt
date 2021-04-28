package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.Channel
import com.example.youtube_clone.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChannelRepository : JpaRepository<Channel, Int> {
  fun findByIdAndUser(id: Int, user: User): Optional<Channel>
}
