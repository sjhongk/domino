package io.oopy.domino.domain.stock

data class Indicators(
    val quote: List<Quote>,
    val adjclose: List<Adjclose>
)
