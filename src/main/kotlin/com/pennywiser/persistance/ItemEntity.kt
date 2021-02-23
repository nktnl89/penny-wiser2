package com.pennywiser.persistance

import javax.persistence.*

@Entity
@Table(name = "item")
data class ItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String,
    var active: Boolean
)