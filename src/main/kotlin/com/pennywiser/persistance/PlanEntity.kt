package com.pennywiser.persistance

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "plan")
data class PlanEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: ItemEntity,
    var sum: Double,
    var effectiveTo: LocalDate,
    var active: Boolean
    //var currentSum: Double
)