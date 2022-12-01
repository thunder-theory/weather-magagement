package com.weathermagagement.controller

import com.weathermagagement.domain.Work
import com.weathermagagement.dto.AddWorkDto
import com.weathermagagement.dto.usernameDto
import com.weathermagagement.service.WorkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/work")
class WorkController(
    private val workService: WorkService
) {

    @PostMapping("/index")
    @ResponseStatus(HttpStatus.OK)
    fun index(@RequestBody usernameDto: usernameDto): List<Work>{
        return workService.indexWorks(usernameDto)
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    fun add(@RequestBody addWorkDto: AddWorkDto) {
        workService.addWork(addWorkDto)
    }
}