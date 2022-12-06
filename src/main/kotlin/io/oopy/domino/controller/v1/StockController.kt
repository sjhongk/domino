package io.oopy.domino.controller.v1

import io.oopy.domino.adapter.StockAdapter
import io.oopy.domino.domain.stock.Chart
import io.oopy.domino.domain.stock.StockInfo
import io.oopy.domino.domain.stock.StockSymbol
import io.oopy.domino.service.StockService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/v1")
class StockController(private val stockService: StockService) {
    @GetMapping("/stock-info")
    fun getSamsungStockInfoByIntervalAndRange(
        @RequestParam(required = false, defaultValue = "1d") interval: String,
        @RequestParam(required = false, defaultValue = "5d") range: String,
        response: HttpServletResponse
    ): List<StockInfo>? {
        val request = StockAdapter.StockRequest(
            symbol = StockSymbol.SAMSUNG.code,
            interval = interval,
            range = range
        )
        if (!stockService.validateStockRequest(request)) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return null
        }
        return stockService.getStockInfoByIntervalAndRange(request)
    }
}