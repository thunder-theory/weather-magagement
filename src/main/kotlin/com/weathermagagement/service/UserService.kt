package com.weathermagagement.service

import com.weathermagagement.domain.User
import com.weathermagagement.domain.repository.UserRepository
import com.weathermagagement.dto.SignupRequest
import com.weathermagagement.dto.UpdateUserRequestDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
@Transactional
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {

    fun signUp(loginRequest: SignupRequest) {
        userRepository.findByUsername(loginRequest.username)
            ?.let { throw IllegalArgumentException("이미 존재하는 아이디입니다.") }

        val user = User(
            username = loginRequest.username,
            password = passwordEncoder.encode(loginRequest.password),
            place = loginRequest.place
        )
        userRepository.save(user)
    }

    fun updatePlace(updateUserRequestDto: UpdateUserRequestDto){
        val user = userRepository.findByUsername(updateUserRequestDto.username)
            ?.let {it.place = updateUserRequestDto.place }
        userRepository.flush()
    }

}