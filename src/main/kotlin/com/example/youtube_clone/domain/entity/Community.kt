package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "community"
)
@TableGenerator(
        name = "community_seq_generator",
        table = "community_sequences",
        pkColumnValue = "community_seq",
        allocationSize = 1
)
data class Community(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "community_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "description",
                nullable = false,
                length = 500
        )
        private val description: String,

        @Column(
                name = "likes",
                nullable = false
        )
        private val likes: Int = 0,

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
        @JoinColumn(name = "channel_id")
        private val channel: Channel,

        @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        private val images: MutableSet<CommunityImage>
)
