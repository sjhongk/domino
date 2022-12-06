package io.oopy.domino.controller.v2

import io.oopy.domino.adapter.StockAdapter
import io.oopy.domino.domain.stock.Chart
import io.oopy.domino.domain.stock.StockInfo
import io.oopy.domino.service.StockService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/v2")
class StockV2Controller(private val stockService: StockService) {
    @GetMapping("/stock-info/{symbol}")
    fun getStockInfoByIntervalAndRange(
        @PathVariable symbol: String,
        @RequestParam(required = false, defaultValue = "1d") interval: String,
        @RequestParam(required = false, defaultValue = "5d") range: String,
        response: HttpServletResponse
    ): List<StockInfo>? {
        val request = StockAdapter.StockRequest(
            symbol = symbol,
            interval = interval,
            range = range
        )
        if (!stockService.validateStockRequest(request)) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return null
        }
        return stockService.getStockInfoByIntervalAndRange(request)
    }

    @GetMapping("/chart/{symbol}")
    fun getChartByIntervalAndRange(
        @PathVariable symbol: String,
        @RequestParam(required = false, defaultValue = "1d") interval: String,
        @RequestParam(required = false, defaultValue = "5d") range: String,
        response: HttpServletResponse
    ): Chart? {
        val request = StockAdapter.StockRequest(
            symbol = symbol,
            interval = interval,
            range = range
        )
        if (!stockService.validateStockRequest(request)) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return null
        }
        return stockService.getChartByIntervalAndRange(request)
    }
}