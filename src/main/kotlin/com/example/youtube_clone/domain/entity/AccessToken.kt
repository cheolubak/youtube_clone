package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
        name = "access_token"
)
data class AccessToken(
        @Id
        private val token: String,

        @Column(
                name = "client_key",
                unique = true,
                nullable = false
        )
        private val clientKey: String,

        @Column(
                name = "ip",
                nullable = false
        )
        private val ip: String,

        @CreationTimestamp
        @Column(
                name = "created_at"
        )
        private val createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(
                name = "updated_at"
        )
        private val updatedAt: LocalDateTime = LocalDateTime.now(),

        @Column(
                name = "expired_at"
        )
        private val expiredAt: LocalDateTime,

        @ManyToOne
        @JoinColumn(
                name = "user_id",
                nullable = false
        )
        private val user: User
) {
    fun getToken(): String {
        return token
    }

    fun getUser(): User {
        return user
    }
}