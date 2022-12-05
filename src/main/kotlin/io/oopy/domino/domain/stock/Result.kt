package io.oopy.domino.domain.stock

data class Result(
    val meta: Meta,
    val timestamp: List<Long>,
    val indicators: Indicators
)
