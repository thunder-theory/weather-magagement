package com.weathermagagement.service

import com.weathermagagement.domain.Work
import com.weathermagagement.domain.repository.WorkRepository
import com.weathermagagement.dto.AddWorkDto
import com.weathermagagement.dto.usernameDto
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

    fun indexWorks(usernameDto: usernameDto): List<Work> {
        val username = usernameDto.username
        return workRepository.findAllByUsername(username)

    }
}