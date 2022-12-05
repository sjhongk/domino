package io.oopy.domino.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api")
data class ApiConfig (
    var endpoint: Map<String, String> = mapOf()
)

enum class EndPoint(val value : String) {
    STOCK("stock")
}