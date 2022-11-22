package com.weathermagagement.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.security.Key
import java.util.*
import java.util.stream.Collectors

class TokenProvider {
    private val log = KotlinLogging.logger {}
    protected val secret: String
    protected val key: Key
    protected val tokenValidityInMilliseconds: Long

    constructor(secret: String, tokenValidityInMilliseconds: Long) {
        this.secret = secret
        val keyBytes: ByteArray = Base64.getDecoder().decode(secret)
        this.key = Keys.hmacShaKeyFor(keyBytes)
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000
    }
    companion object {
        val AUTHORITIES_KEY = "auth"
    }


    fun createToken(authentication: Authentication): String? {
        val authorities = authentication.authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","))

        val now: Long = Date().time
        val validity: Date = Date(this.tokenValidityInMilliseconds + now)

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(validity)
            .compact()
    }

    fun authenticate(token: String): Authentication {
        val claims: Claims = Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

//        val authorities: Collection<Class<out GrantedAuthority>> =

        val authorities: List<out GrantedAuthority> = claims.get(AUTHORITIES_KEY).toString().split(",")
            .map { it -> SimpleGrantedAuthority(it) }
            .toList()

        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }


    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
            return true
        } catch (e: MalformedJwtException) {
            log.info { "잘못된 JWT서명입니다." }
        } catch (e: SecurityException) {
            log.info { "잘못된 JWT서명입니다." }
        } catch (e: UnsupportedJwtException) {
            log.info { "지원되지 않는 JWT 토큰입니다." }
        } catch (e: IllegalArgumentException) {
            log.info { "JWT 토큰이 잘못되었습니다." }
        }
        return false
    }
}