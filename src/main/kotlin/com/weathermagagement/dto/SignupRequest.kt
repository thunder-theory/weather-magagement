package com.weathermagagement.dto

data class SignupRequest(
    val username: String,
    val password: String,
    val place: String,
)
