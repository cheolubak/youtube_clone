package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "play_list"
)
@TableGenerator(
        name = "play_list_seq_generator",
        table = "play_list_sequences",
        pkColumnValue = "play_list_seq",
        allocationSize = 1
)
data class PlayList(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "play_list_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "name",
                nullable = false,
                length = 20
        )
        private val name: String,

        @Column(
                name = "count",
                nullable = false
        )
        private val count: Int = 0,

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

        @ManyToOne
        @JoinColumn(
                name = "channel_id",
                nullable = false
        )
        private val channel: Channel,

        @OneToMany(mappedBy = "playList", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        private val videos: MutableSet<Video>
)
