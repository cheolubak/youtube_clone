package com.example.youtube_clone.domain.entity

import javax.persistence.*

@Entity
@Table(
        name = "channel"
)
@TableGenerator(
        name = "channel_seq_generator",
        table = "channel_sequences",
        pkColumnValue = "channel_seq",
        allocationSize = 1
)
class Channel(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "channel_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "profile"
        )
        private val profile: String,

        @Column(
                name = "thumbnail"
        )
        private val thumbnail: String,

        @Column(
                name = "subscribes",
                nullable = false
        )
        private val subscribes: Int = 0,

        @OneToOne
        @JoinColumn(
                name = "representative_video_id"
        )
        private val representativeVideo: Video,

        @ManyToOne
        @JoinColumn(
                name = "user_id"
        )
        private val user: User
) {
}
