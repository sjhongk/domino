package io.oopy.domino.controller.v1

import io.oopy.domino.adapter.StockAdapter
import io.oopy.domino.domain.stock.Chart
import io.oopy.domino.service.StockService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class StockController(private val stockService: StockService) {
    @GetMapping("v1/chart")
    fun getStockByIntervalAndRange(
        @RequestParam(required = false, defaultValue = "005930.KS") symbol: String,
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