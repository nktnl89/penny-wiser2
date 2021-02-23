package com.pennywiser.service

import com.pennywiser.domain.FactAnalysisReport
import com.pennywiser.persistance.ItemEntity
import com.pennywiser.persistance.PlanEntity
import com.pennywiser.persistance.repo.EntryRepo
import com.pennywiser.persistance.repo.PlanRepo
import com.pennywiser.util.toDomain
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDate

@Service
class PlanService(
    private val planRepo: PlanRepo,
    private val entryRepo: EntryRepo,
    private val clock: Clock
) {
    fun getCurrentPlan(currentDate: LocalDate): List<PlanEntity> = planRepo.getActivePlan(currentDate)
        ?: throw IllegalArgumentException("No active plan found for $currentDate")

    fun getCurrentPlan(item: ItemEntity, currentDate: LocalDate): PlanEntity =
        planRepo.getActivePlan(item, currentDate)
            ?: throw IllegalArgumentException("No active plan found for ${item.name}")

    fun save(plan: PlanEntity): PlanEntity = planRepo.save(plan)
    fun setActive(planId: Long, active: Boolean) = planRepo.setActive(planId, active)

    fun prepareFactAnalysis(to: LocalDate): List<FactAnalysisReport> {
        val currentDate = LocalDate.now(clock)
        val plans = planRepo.getActivePlan(currentDate)
        val entries = entryRepo.fetchEntries(currentDate.withDayOfMonth(1), currentDate)
        val itemsExpenses = entries.groupBy({ it.item }, { it.sum })

        val factAnalysis = mutableListOf<FactAnalysisReport>()
        plans?.forEach { plan ->
            val factSum = itemsExpenses[plan.item]?.sum() ?: 0.0
            factAnalysis.add(FactAnalysisReport(plan.item.toDomain(), plan.sum, factSum, plan.sum - factSum))
        }
        return factAnalysis
    }
}