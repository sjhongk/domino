package io.oopy.domino.domain.stock

data class CurrentTradingPeriod(
    val pre: CurrentTradingPeriodData,
    val regular: CurrentTradingPeriodData,
    val post: CurrentTradingPeriodData
)