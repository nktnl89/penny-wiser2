package com.pennywiser.persistance

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "entry")
data class EntryEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var processed: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: ItemEntity,
    var sum: Double
)