package com.example.youtube_clone.domain.entity

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
class Video(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "video_seq_generator"
        )
        private val id: Int
) {
}