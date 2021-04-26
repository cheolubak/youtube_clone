package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*


@Entity
@Table(
        name = "tag"
)
@TableGenerator(
        name = "tag_seq_generator",
        table = "tag_sequences",
        pkColumnValue = "tag_seq",
        allocationSize = 1
)
data class Tag(
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE,
                generator = "tag_seq_generator"
        )
        private val id: Int,

        @Column(
                name = "tag",
                nullable = false
        )
        private val tag: String,

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
        private val updatedAt: Date
)
