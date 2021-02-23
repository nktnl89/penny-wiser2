package com.pennywiser.domain

data class FactAnalysisReport(
    val item: Item,
    val planSum: Double,
    val factSum: Double,
    val difference: Double
)