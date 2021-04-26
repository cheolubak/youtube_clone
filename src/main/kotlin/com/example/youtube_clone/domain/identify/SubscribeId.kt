package com.example.youtube_clone.domain.identify

import com.example.youtube_clone.domain.entity.Channel
import java.io.Serializable
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class SubscribeId(
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
) : Serializable
