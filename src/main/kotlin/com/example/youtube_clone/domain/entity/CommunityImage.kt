package com.example.youtube_clone.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "community_image"
)
@TableGenerator(
        name = "community_image_seq_generator",
        table = "community_image_sequences",
        pkColumnValue = "community_image_seq",
        allocationSize = 1
)
data class CommunityImage(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "community_image_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "url",
                nullable = false
        )
        private val url: String,

        @Column(
                name = "is_delete",
                nullable = false
        )
        private val isDelete: Boolean = false,

        @Column(
                name = "created_at",
                nullable = false
        )
        private val createdAt: Date,

        @Column(
                name = "updated_at",
                nullable = false
        )
        private val updatedAt: Date,

        @ManyToOne
        @JoinColumn(name = "community_id")
        private val community: Community
)
