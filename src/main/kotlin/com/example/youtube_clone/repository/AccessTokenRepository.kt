package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.AccessToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccessTokenRepository : JpaRepository<AccessToken, String> {
}