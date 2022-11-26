package com.weathermagagement.security

import com.weathermagagement.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


class AccountAdapter(
    val user: User
): org.springframework.security.core.userdetails.User(user.username, user.password, authorities()) {

    companion object{
        private fun authorities(): Set<GrantedAuthority> {
            val authorities = mutableListOf<String>()

            authorities.add("abc")

            return authorities.map { it -> SimpleGrantedAuthority(it) }
                .toSet()
        }
    }


}