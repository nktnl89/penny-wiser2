package com.pennywiser.util

import com.pennywiser.domain.Entry
import com.pennywiser.domain.Item
import com.pennywiser.domain.Plan
import com.pennywiser.persistance.EntryEntity
import com.pennywiser.persistance.ItemEntity
import com.pennywiser.persistance.PlanEntity
import org.springframework.http.ResponseEntity

fun Plan.toEntity(): PlanEntity = PlanEntity(id, item.toEntity(), sum, effectiveTo, active)

fun Item.toEntity(): ItemEntity = ItemEntity(id, name, active)

fun Entry.toEntity(): EntryEntity = EntryEntity(id, processed, item.toEntity(), sum)

fun PlanEntity.toDomain(): Plan = Plan(id, item.toDomain(), sum, effectiveTo, active)

fun ItemEntity.toDomain(): Item = Item(id, name, active)

fun EntryEntity.toDomain(): Entry = Entry(id, processed, item.toDomain(), sum)