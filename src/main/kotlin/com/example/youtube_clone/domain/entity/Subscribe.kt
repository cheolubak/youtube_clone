package com.example.youtube_clone.domain.entity

import com.example.youtube_clone.domain.identify.SubscribeId
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "subscribe",
)
@IdClass(SubscribeId::class)
data class Subscribe(
        @Id
        @ManyToOne
        @JoinColumn(
                name = "channel_id"
        )
        private val channel: Channel,

        @Id
        @ManyToOne
        @JoinColumn(
                name = "to_channel_id"
        )
        private val toChannel: Channel,

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(
                name = "created_at",
                nullable = false
        )
        private val created_at: Date
)
