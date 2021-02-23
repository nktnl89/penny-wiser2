package com.pennywiser.web

import com.pennywiser.domain.Item
import com.pennywiser.service.ItemService
import com.pennywiser.util.toDomain
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/items"])
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping(value = ["/{itemId}"])
    fun getItemById(@PathVariable itemId: Long): ResponseEntity<Item> {
        return ResponseEntity.ok(itemService.getById(itemId).toDomain())
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getItems(): ResponseEntity<List<Item>> {
        return ResponseEntity.ok(itemService.getActiveItems().map { it.toDomain() })
    }

    @RequestMapping(method = [RequestMethod.POST, RequestMethod.PUT], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveItem(@RequestBody item: Item): ResponseEntity<Item> {
        return ResponseEntity.ok(itemService.saveItem(item).toDomain())
    }

    @Transactional
    @DeleteMapping(value = ["/{itemId}"])
    fun deactivateItem(@PathVariable itemId: Long): ResponseEntity<Void> =
        itemService.deactivateItem(itemId).let { ResponseEntity.ok().build() }
}