package io.oopy.domino.service

import io.oopy.domino.adapter.StockAdapter
import io.oopy.domino.domain.stock.*
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class StockService(private val stockAdapter: StockAdapter) {
    fun getChartByIntervalAndRange(request: StockAdapter.StockRequest): Chart {
        return stockAdapter.getChartByIntervalAndRange(request).chart
    }
    fun getStockInfoByIntervalAndRange(request: StockAdapter.StockRequest): List<StockInfo> {
        val chart = getChartByIntervalAndRange(request)
        val result: Result = chart.result.firstOrNull() ?: throw Exception("The data contains empty values.")

        val timestamps: List<Long> = result.timestamp
        val quote: Quote = result.indicators.quote.firstOrNull() ?: throw Exception("The data contains empty values.")

        if (timestamps.size != quote.getSize()) {
            throw Exception("The number of timestamp and quote must be the same.")
        }

        val stockInfos = mutableListOf<StockInfo>()

        timestamps.forEachIndexed { index, l ->
            stockInfos.add(
                StockInfo(
                    high = quote.high[index],
                    low = quote.low[index],
                    open = quote.open[index],
                    close = quote.close[index],
                    volume = quote.volume[index],
                    timestamp = l
                )
            )
        }

        return stockInfos
    }

    fun validateStockRequest(request: StockAdapter.StockRequest): Boolean {
        if (!StockSymbol.values().map { it.code }.contains(request.symbol)) {
            logger.error { "symbol can only have values: ${StockSymbol.values().map { it.code }}" }
            return false
        }
        if (!Interval.values().map { it.value }.contains(request.interval)) {
            logger.error { "interval can only have values: ${Interval.values().map { it.value }}" }
            return false
        }
        if (!Range.values().map { it.value }.contains(request.range)) {
            logger.error { "range can only have values: ${Range.values().map { it.value }}" }
            return false
        }
        return true
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}