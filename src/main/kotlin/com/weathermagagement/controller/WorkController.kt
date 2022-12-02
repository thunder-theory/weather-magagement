package com.weathermagagement.controller

import com.weathermagagement.domain.Work
import com.weathermagagement.dto.AddWorkDto
import com.weathermagagement.dto.UsernameDto
import com.weathermagagement.dto.WorkByDateDto
import com.weathermagagement.dto.WorkByMonthDto
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

    @PostMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(@RequestBody usernameDto: UsernameDto): List<Work>{
        return workService.findAllWorks(usernameDto)
    }

    @PostMapping("/findByDate")
    @ResponseStatus(HttpStatus.OK)
    fun find(@RequestBody workByDateDto: WorkByDateDto ): List<Work> {
        return workService.findWorksByDate(workByDateDto)
    }

    @PostMapping("/findByMonth")
    @ResponseStatus(HttpStatus.OK)
    fun find(@RequestBody workByMonthDto: WorkByMonthDto): List<Work> {
        return workService.findWorksByMonth(workByMonthDto)
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    fun add(@RequestBody addWorkDto: AddWorkDto) {
        workService.addWork(addWorkDto)
    }
}