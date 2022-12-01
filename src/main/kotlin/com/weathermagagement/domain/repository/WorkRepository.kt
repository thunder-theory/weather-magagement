package com.weathermagagement.domain.repository

import com.weathermagagement.domain.Work
import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository: JpaRepository<Work, Long> {
}