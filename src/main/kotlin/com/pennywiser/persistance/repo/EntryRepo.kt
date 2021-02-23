package com.pennywiser.persistance.repo

import com.pennywiser.persistance.EntryEntity
import com.pennywiser.persistance.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface EntryRepo : JpaRepository<EntryEntity, Long> {

    @Query("select e from EntryEntity e where e.processed between ?1 and ?2")
    fun fetchEntries(from: LocalDate, to: LocalDate): List<EntryEntity>
    @Query("select e from EntryEntity e where e.processed between ?2 and ?3 and e.item = ?1")
    fun fetchEntries(item: ItemEntity, from: LocalDate, to: LocalDate): List<EntryEntity>
}