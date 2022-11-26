package com.weathermagagement.security.config

import mu.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryFilter: AuthenticationEntryPoint{

    val log = KotlinLogging.logger {  }

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        log.info { "=================== 401 Error 발생!! ===================" }
            response?.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}