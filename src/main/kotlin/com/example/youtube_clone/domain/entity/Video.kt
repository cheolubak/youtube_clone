package com.example.youtube_clone.domain.entity

import com.example.youtube_clone.domain.enum.VideoStatusType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "video"
)
@TableGenerator(
        name = "video_seq_generator",
        table = "video_sequences",
        pkColumnValue = "video_seq",
        allocationSize = 1
)
data class Video(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "video_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "name",
                nullable = false,
                length = 100
        )
        private val name: String,

        @Column(
                name = "thumbnail",
                nullable = false,
                length = 100
        )
        private val thumbnail: String,

        @Column(
                name = "description",
                nullable = false,
                length = 500
        )
        private val description: String,

        @Column(
                name = "duration",
                nullable = false
        )
        private val duration: Int,

        @Column(
                name = "likes",
                nullable = false
        )
        private val like: Int = 0,

        @Column(
                name = "bads",
                nullable = false
        )
        private val bads: Int = 0,

        @Column(
                name = "comments",
                nullable = false
        )
        private val comments: Int = 0,

        @Column(
                name = "views",
                nullable = false
        )
        private val views: Int = 0,

        @Enumerated(EnumType.STRING)
        @Column(
                name = "status",
                nullable = false
        )
        private val status: VideoStatusType = VideoStatusType.READY,

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
        private val updatedAt: Date,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(
                name = "channel_id",
                nullable = false
        )
        private val channel: Channel,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(
                name = "play_list_id"
        )
        private val playList: PlayList
)
