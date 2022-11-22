package com.weathermagagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WeatherMagagementApplication

fun main(args: Array<String>) {
    runApplication<WeatherMagagementApplication>(*args)
}
