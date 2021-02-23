package com.pennywiser.domain

data class Item(
    val id: Long?,
    val name: String,
    val active: Boolean = false
)