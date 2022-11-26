package com.weathermagagement.security.config

import com.weathermagagement.security.TokenProvider
import com.weathermagagement.security.filter.JwtFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class JwtSecurityConfig(
    val tokenProvider: TokenProvider,
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity?) {
        val customFilter: JwtFilter = JwtFilter(tokenProvider)
        builder?.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}