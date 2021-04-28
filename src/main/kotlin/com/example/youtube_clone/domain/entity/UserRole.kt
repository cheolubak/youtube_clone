package com.example.youtube_clone.domain.entity

import com.example.youtube_clone.domain.enum.UserRoleType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
        name = "user_role"
)
data class UserRole(
        @Id
        @OneToOne
        @JoinColumn(
                name = "user_id"
        )
        private val user: User,

        @Enumerated(EnumType.STRING)
        @Column(
                name = "role"
        )
        private val role: UserRoleType,

        @CreationTimestamp
        @Column(
                name = "created_at"
        )
        private val createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(
                name = "updated_at"
        )
        private val updatedAt: LocalDateTime = LocalDateTime.now()
) : Serializable {
}