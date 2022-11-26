package com.weathermagagement.security.service

import com.weathermagagement.domain.repository.UserRepository
import com.weathermagagement.security.AccountAdapter
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class CustomDetailsService: UserDetailsService {
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = (userRepository.findByUsername(username!!)
            ?: throw UsernameNotFoundException("해당 유저를 찾지 못했습니다."))

        return AccountAdapter(user)
    }
}