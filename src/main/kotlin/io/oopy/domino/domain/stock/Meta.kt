package io.oopy.domino.domain.stock

data class Meta(
    val currency: String,
    val symbol: String,
    val exchangeName: String,
    val instrumentType: String,
    val firstTradeDate: Long,
    val regularMarketTime: Long,
    val gmtoffset: Long,
    val timezone: String,
    val exchangeTimezoneName: String,
    val regularMarketPrice: Long,
    val chartPreviousClose: Long,
    val priceHint: Long,
    val currentTradingPeriod: CurrentTradingPeriod,
    val dataGranularity: String,
    val range: String,
    val validRanges: List<String>
)
