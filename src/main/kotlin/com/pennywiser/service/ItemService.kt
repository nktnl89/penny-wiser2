package com.pennywiser.service

import com.pennywiser.domain.Item
import com.pennywiser.persistance.ItemEntity
import com.pennywiser.persistance.repo.ItemRepo
import com.pennywiser.util.toEntity
import com.pennywiser.util.toDomain
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepo: ItemRepo
) {
    fun getById(id: Long): ItemEntity = itemRepo.findById(id).orElseThrow { throw IllegalArgumentException("No item found for $id") }

    fun saveItem(item: Item): ItemEntity = itemRepo.save(item.toEntity())
    fun deactivateItem(itemId: Long) = itemRepo.deactivateItemById(itemId)
    fun getActiveItems(): List<ItemEntity> = itemRepo.getActiveItems()
}