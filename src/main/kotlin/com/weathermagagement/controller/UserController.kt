package com.weathermagagement.controller

import com.weathermagagement.dto.LoginRequestDto
import com.weathermagagement.dto.SignupRequest
import com.weathermagagement.dto.UpdateUserRequestDto
import com.weathermagagement.security.service.AuthService
import com.weathermagagement.service.UserService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val authService: AuthService,
) {

    val log = KotlinLogging.logger {  }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun signup(@RequestBody loginRequest: SignupRequest) {
        userService.signUp(loginRequest)
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody dto: LoginRequestDto)
    = authService.authenticate(username = dto.username,password = dto.password)

    @PostMapping("/update_user")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@RequestBody dto: UpdateUserRequestDto){
        userService.updatePlace(dto)
    }


    @GetMapping("/test2")
    fun test() = "test"
}