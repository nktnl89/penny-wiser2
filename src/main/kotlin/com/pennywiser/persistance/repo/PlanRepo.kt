package com.pennywiser.persistance.repo

import com.pennywiser.persistance.ItemEntity
import com.pennywiser.persistance.PlanEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface PlanRepo : JpaRepository<PlanEntity, Long> {

    @Query("select p from PlanEntity p " +
            "inner join fetch p.item " +
            "where p.active = true and p.effectiveTo >= ?1")
    fun getActivePlan(currentDate: LocalDate): List<PlanEntity>?

    @Query("select p from PlanEntity p " +
            "inner join fetch p.item " +
            "where p.active = true and p.effectiveTo >= ?2 and p.item = ?1")
    fun getActivePlan(item: ItemEntity, currentDate: LocalDate): PlanEntity

    @Modifying
    @Query("update PlanEntity p set p.active = ?2 where p.id = ?1")
    fun setActive(planId: Long, active: Boolean)
}