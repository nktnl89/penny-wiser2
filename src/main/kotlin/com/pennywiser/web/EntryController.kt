package com.pennywiser.web

import com.pennywiser.domain.Entry
import com.pennywiser.domain.Item
import com.pennywiser.service.EntryService
import com.pennywiser.util.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping(value = ["/entries"])
class EntryController(
    private val entryService: EntryService
) {
    fun saveEntry(entry: Entry): ResponseEntity<Entry> = ResponseEntity.ok(entryService.saveEntry(entry).toDomain())
    fun deleteEntry(entryId: Long): ResponseEntity<Void> =
        entryService.deleteEntry(entryId).let { ResponseEntity.ok().build() }

    fun fetchEntries(from: LocalDate, to: LocalDate): ResponseEntity<List<Entry>> =
        ResponseEntity.ok(entryService.fetchEntries(from, to).map { it.toDomain() })

    fun fetchEntries(item: Item, from: LocalDate, to: LocalDate): ResponseEntity<List<Entry>> =
        ResponseEntity.ok(entryService.fetchEntries(item, from, to).map { it.toDomain() })
}