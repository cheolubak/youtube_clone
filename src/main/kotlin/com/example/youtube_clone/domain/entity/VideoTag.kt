package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "video_tag"
)
data class VideoTag(
        @Id
        @ManyToOne
        @JoinColumn(name = "video_id")
        private val video: Video,

        @ManyToOne
        @JoinColumn(name = "tag_id")
        private val tag: Tag,

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(
                name = "created_at",
                nullable = false
        )
        private val createdAt: Date
) : Serializable
