package com.example.youtube_clone.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
    name = "community_comment"
)
@TableGenerator(
    name = "community_comment_seq_generator",
    table = "community_comment_sequences",
    pkColumnValue = "community_comment_seq",
    allocationSize = 1
)
data class CommunityComment(
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator = "community_comment_seq_generator"
    )
    private val id: Int,

    @Column(
        name = "comment",
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
        name = "community_id",
        nullable = false
    )
    private val community: Community,

    @OneToMany(mappedBy = "communityComment", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private val communityCommentReply: Set<CommunityCommentReply>
) {
}
