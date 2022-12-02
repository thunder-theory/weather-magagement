package com.weathermagagement.service

import com.weathermagagement.domain.Work
import com.weathermagagement.domain.repository.WorkRepository
import com.weathermagagement.dto.AddWorkDto
import com.weathermagagement.dto.UsernameDto
import com.weathermagagement.dto.WorkByDateDto
import com.weathermagagement.dto.WorkByMonthDto
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class WorkService(
    val workRepository: WorkRepository,
) {
    fun addWork(addWorkDto: AddWorkDto){
        val work = Work(
            username = addWorkDto.username,
            year = addWorkDto.year,
            month = addWorkDto.month,
            day = addWorkDto.day,
            topic = addWorkDto.topic,
            contents = addWorkDto.contents,
            pay = addWorkDto.pay
        )
        workRepository.save(work)
    }

    fun findAllWorks(usernameDto: UsernameDto): List<Work> {
        val username = usernameDto.username
        return workRepository.findAllByUsername(username)

    }

    fun findWorksByDate(workByDateDto: WorkByDateDto): List<Work>{
        return workRepository.findAllByUsernameAndYearAndMonthAndDay(
            username = workByDateDto.username,
            year = workByDateDto.year,
            month = workByDateDto.month,
            day = workByDateDto.day
        )
    }

    fun findWorksByMonth(workByMonthDto: WorkByMonthDto): List<Work>{
        return workRepository.findAllByUsernameAndYearAndMonth(
            username = workByMonthDto.username,
            year = workByMonthDto.year,
            month = workByMonthDto.Month
        )
    }
}