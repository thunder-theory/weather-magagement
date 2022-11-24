package com.weathermagagement.security.util

import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.Optional

class SecurityUtil {

    companion object {
        val logger = KotlinLogging.logger {}
        fun getCurrentUsername(): Optional<String> {
            val authentication: Authentication = SecurityContextHolder.getContext().authentication
            if (authentication == null) {
                logger.info { "Security Context에 인증 정보가 없습니다." }
            }
            var username: String? = null

            if (authentication.principal is UserDetails) {
                val springSecurityUser: UserDetails = authentication.principal as UserDetails
            }else if (authentication.principal is String) {
                username = authentication.principal as String
            }
            return Optional.ofNullable(username)
        }
    }

}