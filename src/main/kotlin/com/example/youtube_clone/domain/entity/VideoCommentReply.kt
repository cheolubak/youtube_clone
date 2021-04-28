package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
    name = "video_comment_reply"
)
@TableGenerator(
    name = "video_comment_reply_seq_generator",
    table = "video_comment_reply_sequences",
    pkColumnValue = "video_comment_reply_seq",
    allocationSize = 1
)
data class VideoCommentReply(
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator = "video_comment_reply_seq_generator"
    )
    private val id: Int,

    @Column(
        name = "reply",
        nullable = false
    )
    private val reply: String,

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
        name = "is_delete",
        nullable = false
    )
    private val isDelete: Boolean = false,

    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false
    )
    private val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(
        name = "updated_at",
        nullable = false
    )
    private val updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private val user: User,

    @ManyToOne
    @JoinColumn(
        name = "video_comment_id",
        nullable = false
    )
    private val videoComment: VideoComment
) {
}
