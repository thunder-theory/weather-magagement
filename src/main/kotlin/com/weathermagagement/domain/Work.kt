package com.weathermagagement.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "work")
data class Work(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "username", length = 250)
    var username: String,

    @Column
    val year: Int,

    @Column
    val month: Int,

    @Column
    val day: Int,

    @Column
    val topic: String,

    @Column
    val contents: String,

    @Column
    val pay: Long,
)
