package com.weathermagagement.security.service

import com.weathermagagement.domain.User
import com.weathermagagement.domain.repository.UserRepository
import com.weathermagagement.dto.ResponseLoginDto
import com.weathermagagement.security.TokenProvider
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@RequiredArgsConstructor
@Service
class AuthService {

    private lateinit var tokenProvider: TokenProvider
    private lateinit var authenticationManagerBuilder: AuthenticationManagerBuilder
    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder

    @Transactional
    fun authenticate(username: String, password: String): ResponseLoginDto {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("해당 유저가 없습니다.")
        matchPassword(password, user)

        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)

        val authenticate: Authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        SecurityContextHolder.getContext().authentication = authenticate
        val token: String? = tokenProvider.createToken(authenticate)

        return ResponseLoginDto(
            username = user.username,
            token = token,
        )

    }

    private fun matchPassword(password: String, user: User) {
        val matches = passwordEncoder.matches(password, user.password)

        if (!matches) {
            throw IllegalArgumentException("패스워드가 일치하지 않습니다.")
        }
    }
}