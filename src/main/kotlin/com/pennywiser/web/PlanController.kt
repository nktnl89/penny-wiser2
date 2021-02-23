package com.pennywiser.web

import com.pennywiser.domain.FactAnalysisReport
import com.pennywiser.domain.Item
import com.pennywiser.domain.Plan
import com.pennywiser.service.PlanService
import com.pennywiser.util.toEntity
import com.pennywiser.util.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Clock
import java.time.LocalDate

@RestController
@RequestMapping(value = ["/plans"])
class PlanController(
    private var planService: PlanService,
    private var clock: Clock
) {

    fun savePlan(plan: Plan): ResponseEntity<Plan> = ResponseEntity.ok(planService.save(plan.toEntity()).toDomain())

    fun deactivatePlan(planId: Long): ResponseEntity<Void> =
        planService.setActive(planId, false).let { ResponseEntity.ok().build() }

    fun getCurrentActivePlan(item: Item): ResponseEntity<Plan> =
        ResponseEntity.ok(planService.getCurrentPlan(item.toEntity(), LocalDate.now(clock)).toDomain())

    fun prepareFactAnalysisReport(to: LocalDate): ResponseEntity<List<FactAnalysisReport>> =
        ResponseEntity.ok(planService.prepareFactAnalysis(to))
}