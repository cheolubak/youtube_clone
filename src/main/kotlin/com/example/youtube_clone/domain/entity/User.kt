package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "user"
)
@TableGenerator(
        name = "user_seq_generator",
        table = "user_sequences",
        pkColumnValue = "user_seq",
        allocationSize = 1
)
data class User(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "user_seq_generator"
        )
        private val id: Int = -1,

        @Column(
                name = "email",
                nullable = false,
                unique = true
        )
        private val email: String,

        @Column(
                name = "password",
                nullable = false,
        )
        private val password: String,

        @Column(
                name = "nickname",
                nullable = false,
                length = 20
        )
        private val nickname: String,

        @Column(
                name = "profile",
                nullable = true
        )
        private val profile: String? = null,

        @CreationTimestamp
        @Column(
                name = "created_at",
                nullable = false
        )
        private val createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(
                name = "updated_at",
                nullable = false
        )
        private val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun getId(): Int {
        return id
    }

    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun getNickname(): String {
        return nickname
    }

    fun getProfile(): String? {
        return profile
    }

    fun getCreatedAt(): LocalDateTime {
        return createdAt
    }

    fun getUpdatedAt(): LocalDateTime {
        return updatedAt
    }
}
