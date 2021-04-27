package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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
        private val id: Int,

        @Column(
                name = "email",
                nullable = false,
                unique = true
        )
        private val email: String,

        @Column(
                name = "password",
                nullable = false
        )
        private val password: String,

        @Column(
                name = "nickname",
                nullable = false,
        )
        private val nickname: String,

        @Column(
                name = "profile",
                nullable = false
        )
        private val profile: String,

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(
                name = "created_at",
                nullable = false
        )
        private val createdAt: Date,

        @UpdateTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(
                name = "updated_at",
                nullable = false
        )
        private val updatedAt: Date
) {
    fun getPassword(): String {
        return password
    }
}