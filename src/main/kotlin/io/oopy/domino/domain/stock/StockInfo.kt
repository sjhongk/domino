package io.oopy.domino.domain.stock

/**
 * @param high: 최고가격
 * @param low: 최저가격
 * @param open: 개장가격
 * @param close: 종가가격
 * @param volume: 거래량
 * @param timestamp: 거래일자
 */
data class StockInfo(
    val high: Long?,
    val low: Long?,
    val open: Long?,
    val close: Long?,
    val volume: Long?,
    val timestamp: Long
)
