package io.oopy.domino.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@ApiIgnore
@RestController
class HomeController {
    @GetMapping("/")
    fun home(): String {
        return "ok"
    }
}