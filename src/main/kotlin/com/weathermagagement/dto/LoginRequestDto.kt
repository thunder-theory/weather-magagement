package com.weathermagagement.dto

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty

data class LoginRequestDto(

    @NotNull
    @NotEmpty
    val username: String,

    @NotNull
    @NotEmpty
    val password: String,
)
