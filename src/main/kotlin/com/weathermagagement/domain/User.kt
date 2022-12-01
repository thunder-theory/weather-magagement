package com.weathermagagement.domain

import javax.persistence.*


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
    var place: String,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name="username")
    private var chartDataWithBenefit: MutableList<Work> = ArrayList<Work>(),
    ) : BaseEntity()
