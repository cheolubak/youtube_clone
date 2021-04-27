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
        @Column(
                name = "name",
                nullable = false
        )
        private val name: String,

        @Column(
                name = "profile"
        )
        private val profile: String? = null,

        @Column(
                name = "thumbnail"
        )
        private val thumbnail: String? = null,

        @Column(
                name = "subscribes",
                nullable = false
        )
        private val subscribes: Int = 0,

        @OneToOne
        @JoinColumn(
                name = "representative_video_id"
        )
        private val representativeVideo: Video? = null,

        @ManyToOne
        @JoinColumn(
                name = "user_id",
                nullable = false
        )
        private val user: User
) {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "channel_seq_generator"
    )
    private val id: Int = 0
}
