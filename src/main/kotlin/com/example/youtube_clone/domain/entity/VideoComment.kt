package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
    name = "video_comment"
)
@TableGenerator(
    name = "video_comment_seq_generator",
    table = "video_comment_sequences",
    pkColumnValue = "video_comment_seq",
    allocationSize = 1
)
data class VideoComment(
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator = "video_comment_seq_generator"
    )
    private val id: Int,

    @Column(
        name = "comment",
        length = 500,
        nullable = false
    )
    private val comment: String,

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
        name = "replies",
        nullable = false
    )
    private val replies: Int = 0,

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
    private val createdAt: LocalDateTime,

    @UpdateTimestamp
    @Column(
        name = "updated_at",
        nullable = false
    )
    private val updatedAt: LocalDateTime,

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private val user: User,

    @OneToMany(
        mappedBy = "videoComment",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    private val videoCommentReplies: Set<VideoCommentReply>
) {
}
