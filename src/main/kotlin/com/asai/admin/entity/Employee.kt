package com.asai.admin.entity

import com.asai.admin.entity.State
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "table_employee")
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    var username: String? = null,
    @Column
    var password: String? = null,
    @Column
    @Enumerated(EnumType.STRING)
    var state: State,
)
