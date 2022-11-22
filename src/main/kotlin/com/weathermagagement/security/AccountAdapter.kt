package com.weathermagagement.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.ArrayList
import java.util.stream.Collectors

class AccountAdapter(
    val user: User
) {
    companion object{
        private fun authorities(): Set<GrantedAuthority>? {
            var authorities  = mutableListOf<String>()
            authorities.add("abc")
            return authorities.stream().map { it -> SimpleGrantedAuthority(it) }
                .collect(Collectors.toSet())

        }
    }
}