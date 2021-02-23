package com.pennywiser.persistance.repo

import com.pennywiser.persistance.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ItemRepo : JpaRepository<ItemEntity, Long> {

    @Query("select i from ItemEntity i where i.active = true order by i.name")
    fun getActiveItems(): List<ItemEntity>

    @Modifying
    @Query("update ItemEntity i set i.active = false where i.id = ?1")
    fun deactivateItemById(id: Long)
}