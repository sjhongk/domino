package io.oopy.domino.domain.stock


data class Chart(
    val result: List<Result>,
    val error: String? = null
)