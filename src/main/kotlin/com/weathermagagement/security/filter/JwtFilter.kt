package com.weathermagagement.security.filter

import com.weathermagagement.security.TokenProvider
import mu.KLogger
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    val tokenProvider: TokenProvider,
    val log: KLogger = KotlinLogging.logger {  }
): GenericFilterBean() {

    companion object {
        val AUTHORIZATION_HEADER = "Authorization"
    }


    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse
        val jwt = resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI

        // 유효성 검증
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt!!)) {
            val authentication: Authentication = tokenProvider.authenticate(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            val token = tokenProvider.createToken(authentication)
            log.info("Security Context에 '{}' 인증 정보를 저장했습니다., uri {}", authentication.credentials.toString(), requestURI)
            httpServletResponse.setHeader(AUTHORIZATION_HEADER, token)
            httpServletResponse.setHeader("USERNAME", authentication.name)
        }else {
            log.info("유효한 JWT 토큰이 없습니다. uri: {}", requestURI)
        }
        chain?.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            log.info { "token = " + bearerToken.substring(0) }
            return bearerToken.substring(7)
        }
        return null
    }
}