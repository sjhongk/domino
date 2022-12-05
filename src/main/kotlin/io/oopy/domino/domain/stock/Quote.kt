package io.oopy.domino.domain.stock

data class Quote(
    val close: List<Long>,
    val open: List<Long>,
    val low: List<Long>,
    val volume: List<Long>,
    val high: List<Long>
)
