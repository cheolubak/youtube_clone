package com.example.youtube_clone.repository

import com.example.youtube_clone.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): Optional<User>
}