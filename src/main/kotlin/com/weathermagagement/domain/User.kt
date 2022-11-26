package com.weathermagagement.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    var id : Long? = null,

    @Column
    val username: String,

    @Column
    val password: String,

    @Column
    val place: String,
    ) : BaseEntity()
