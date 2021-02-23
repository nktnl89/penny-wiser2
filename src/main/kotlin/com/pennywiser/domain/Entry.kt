package com.pennywiser.domain

import java.time.LocalDateTime

data class Entry(
    val id: Long?,
    val processed: LocalDateTime,
    val item: Item,
    val sum: Double
)