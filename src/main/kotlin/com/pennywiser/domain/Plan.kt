package com.pennywiser.domain

import com.pennywiser.persistance.PlanEntity
import java.time.LocalDate

data class Plan(
    val id: Long?,
    val item: Item,
    val sum: Double,
    val effectiveTo: LocalDate,
    val active: Boolean
)