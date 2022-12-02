package com.weathermagagement.domain.repository

import com.weathermagagement.domain.Work
import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository: JpaRepository<Work, Long> {
    fun findAllByUsername(username: String): List<Work>
    fun findAllByUsernameAndYearAndMonthAndDay(username: String, year: Int, month: Int, day: Int): List<Work>
    fun findAllByUsernameAndYearAndMonth(username: String, year: Int, month: Int): List<Work>
}