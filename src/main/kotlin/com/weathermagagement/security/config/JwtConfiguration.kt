package com.weathermagagement.security.config

import com.weathermagagement.security.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration(
    @Value("\${jwt.access-token-validity-in-seconds}")
    var accessTokenValidityInSeconds: Long,

    @Value("\${jwt.secret}")
    var accessTokenSecret: String
) {

    @Bean
    fun tokenProvider(): TokenProvider {
        return TokenProvider(accessTokenSecret, accessTokenValidityInSeconds)
    }
}