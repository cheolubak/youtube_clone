package com.example.youtube_clone.domain.entity

import com.example.youtube_clone.domain.enum.EventTargetType
import com.example.youtube_clone.domain.enum.EventType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
    name = "event_history",
    indexes = [
      Index(name = "target_index", columnList = "target_id")
    ]
)
@TableGenerator(
    name = "event_history_seq_generator",
    table = "event_history_sequences",
    pkColumnValue = "event_history_seq",
    allocationSize = 1
)
data class EventHistory(
    @Enumerated(EnumType.STRING)
    @Column(
        name = "target",
        nullable = false
    )
    private val target: EventTargetType,

    @Enumerated(EnumType.STRING)
    @Column(
        name = "type",
        nullable = false
    )
    private val type: EventType,

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

    @Column(
        name = "target_id",
        nullable = false
    )
    private val targetId: Int
) {
  @Id
  @GeneratedValue(
      strategy = GenerationType.TABLE,
      generator = "event_history_seq_generator"
  )
  private val id: Int = 0
}