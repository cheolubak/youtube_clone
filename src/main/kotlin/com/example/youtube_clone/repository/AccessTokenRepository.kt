package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.AccessToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccessTokenRepository : JpaRepository<AccessToken, String> {
    fun findByTokenAndClientKeyAndIp(
        token: String,
        clientKey: String,
        ip: String
    ): Optional<AccessToken>
}
