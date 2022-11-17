package com.weathermagagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherMagagementApplication

fun main(args: Array<String>) {
    runApplication<WeatherMagagementApplication>(*args)
}
