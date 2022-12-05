package io.oopy.domino.domain.stock

data class CurrentTradingPeriodData(
    val timezone: String,
    val start: Long,
    val end: Long,
    val gmtoffset: Long
)
