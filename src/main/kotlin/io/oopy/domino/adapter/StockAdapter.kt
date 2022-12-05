package io.oopy.domino.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import io.oopy.domino.config.ApiConfig
import io.oopy.domino.config.EndPoint
import io.oopy.domino.domain.stock.Chart
import io.oopy.domino.domain.stock.StockSymbol
import mu.KotlinLogging
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.springframework.stereotype.Service

@Service
class StockAdapter(
    apiConfig: ApiConfig,
    private val objectMapper: ObjectMapper,
    private val okHttpClient: OkHttpClient
) {
    private val endpoint = apiConfig.endpoint.getValue(EndPoint.STOCK.value)

    private fun convertToMap(request: StockRequest): Map<String, String> {
        val entries = mutableMapOf<String, String>()
        entries["interval"] = request.interval
        entries["range"] = request.range
        return entries
    }

    fun getChartByIntervalAndRange(request: StockRequest): StockResponse {
        val params = convertToMap(request)

        val httpBuilder: HttpUrl.Builder = "${endpoint}/${request.symbol}".toHttpUrl().newBuilder()
        for (param in params.entries) {
            httpBuilder.addQueryParameter(param.key, param.value)
        }

        val httpResponse: Response = try {
            okHttpClient.newCall(
                Request.Builder()
                    .url(httpBuilder.build())
                    .get()
                    .build()
            ).execute()
        } catch (ex: Exception) {
            throw Exception("failed to call yahoo finance chart API")
        }

        val statusCode: Int = httpResponse.code

        val responseBody: String? = httpResponse.body?.string()
        if (!httpResponse.isSuccessful) {
            logger.error { "status code: $statusCode, error occurred" }
        }

        return objectMapper.readValue(responseBody, StockResponse::class.java)
    }

    data class StockRequest(
        var symbol: String = StockSymbol.SAMSUNG.code,
        var interval: String = "1d",
        var range: String = "5d"
    )

    data class StockResponse(
        val chart: Chart
    )

    companion object {
        val logger = KotlinLogging.logger {}
    }
}