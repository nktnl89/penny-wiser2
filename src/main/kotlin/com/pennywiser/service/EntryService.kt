package com.pennywiser.service

import com.pennywiser.domain.Entry
import com.pennywiser.domain.Item
import com.pennywiser.persistance.EntryEntity
import com.pennywiser.persistance.repo.EntryRepo
import com.pennywiser.util.toEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class EntryService(
    private val entryRepo: EntryRepo
) {
    fun saveEntry(entry: Entry): EntryEntity = entryRepo.save(entry.toEntity())
    fun deleteEntry(entryId: Long) = entryRepo.deleteById(entryId)
    fun fetchEntries(from: LocalDate, to: LocalDate): List<EntryEntity> = entryRepo.fetchEntries(from, to)
    fun fetchEntries(item: Item, from: LocalDate, to: LocalDate): List<EntryEntity> =
        entryRepo.fetchEntries(item.toEntity(), from, to)
}