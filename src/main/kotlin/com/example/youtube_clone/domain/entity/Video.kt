package com.example.youtube_clone.domain.entity

import com.example.youtube_clone.domain.enum.VideoStatusType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
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
data class Video(
    @Column(
        name = "name",
        nullable = false,
        length = 100
    )
    private val name: String,

    @Column(
        name = "thumbnail_url",
        nullable = false,
        length = 100
    )
    private val thumbnailUrl: String,

    @Column(
        name = "video_url",
        nullable = false
    )
    private val videoUrl: String,

    @Column(
        name = "description",
        nullable = false,
        length = 500
    )
    private val description: String,

    @Enumerated(EnumType.STRING)
    @Column(
        name = "status",
        nullable = false
    )
    private val status: VideoStatusType = VideoStatusType.READY,

    @Column(
        name = "duration",
        nullable = false
    )
    private val duration: Float,

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

    @Column(
        name = "views",
        nullable = false
    )
    private val views: Int = 0,

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

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(
        name = "channel_id",
        nullable = false
    )
    private val channel: Channel,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(
        name = "play_list_id"
    )
    private val playList: PlayList? = null
) {
  @Id
  @GeneratedValue(
      strategy = GenerationType.TABLE,
      generator = "video_seq_generator"
  )
  private val id: Int = 0

  fun getId(): Int {
    return id
  }

  fun getName(): String {
    return name
  }

  fun getThumbnailUrl(): String {
    return thumbnailUrl
  }

  fun getVideoUrl(): String {
    return videoUrl
  }

  fun getDescription(): String {
    return description
  }

  fun getDuration(): Float {
    return duration
  }

  fun getLikes(): Int {
    return likes
  }

  fun getBads(): Int {
    return bads
  }

  fun getComments(): Int {
    return comments
  }

  fun getViews(): Int {
    return views
  }

  fun getCreatedAt(): LocalDateTime {
    return createdAt
  }

  fun getUpdatedAt(): LocalDateTime {
    return updatedAt
  }

}
