package com.weathermagagement.security.service

import com.weathermagagement.domain.User
import com.weathermagagement.domain.repository.UserRepository
import com.weathermagagement.dto.ResponseLoginDto
import com.weathermagagement.security.TokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val tokenProvider: TokenProvider,
) {


    @Transactional(readOnly = true)
    fun authenticate(username: String, password: String): ResponseLoginDto {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("해당 유저가 없습니다.")
        matchPassword(password, user)

        val authenticationToken = UsernamePasswordAuthenticationToken(username, password) as Authentication

//        authenticationManagerBuilder.
//
//        val authenticate = tokenProvider.authenticate(authenticationToken)
//
//        SecurityContextHolder.getContext().authentication = authenticationToken
        val token: String? = tokenProvider.createToken(authenticationToken)

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