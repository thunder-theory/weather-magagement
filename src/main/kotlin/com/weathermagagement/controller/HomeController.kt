package com.weathermagagement.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    val log = KotlinLogging.logger {  }

    @GetMapping("/test")
    fun test():String?{
        log.info { "test start" }
        return "Hello Weather!"
    }
}