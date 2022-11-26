package com.weathermagagement.domain.repository

import com.weathermagagement.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long>{

    fun findByUsername(username: String): User?
}