package com.weathermagagement.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity (

    @CreatedDate
    var createdDate: LocalDateTime? = LocalDateTime.now(),
        )