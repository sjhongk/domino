package io.oopy.domino

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class DominoApplication

fun main(args: Array<String>) {
    runApplication<DominoApplication>(*args)
}
