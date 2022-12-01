package com.weathermagagement.dto

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty

class AddWorkDto(
    val username: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val topic: String,
    val contents: String,
    val pay: Long,
)