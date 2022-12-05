package io.oopy.domino.service

import io.oopy.domino.adapter.StockAdapter
import io.oopy.domino.domain.stock.Chart
import io.oopy.domino.domain.stock.Interval
import io.oopy.domino.domain.stock.Range
import io.oopy.domino.domain.stock.StockSymbol
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class StockService(private val stockAdapter: StockAdapter) {
    fun getChartByIntervalAndRange(request: StockAdapter.StockRequest): Chart {
        return stockAdapter.getChartByIntervalAndRange(request).chart
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