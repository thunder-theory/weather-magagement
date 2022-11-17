package com.weathermagagement.domain

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id : Long? = null,

    @Column
    val username: String,

    @Column
    val password: String,
    )
