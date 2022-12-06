package io.oopy.domino.domain.stock

data class Quote(
    val close: List<Long?>,
    val open: List<Long?>,
    val low: List<Long?>,
    val volume: List<Long?>,
    val high: List<Long?>
) {
    private fun isSameSize(): Boolean {
        val sizeSet = setOf(close.size, open.size, low.size, volume.size, high.size)
        return sizeSet.size == 1
    }
    fun getSize(): Int? {
        return if (isSameSize()) {
            close.size
        } else {
            null
        }
    }
}
