package com.asai.admin.repository

import com.asai.admin.entity.Employee
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, Long> {
    fun findByUsername(username: String): Employee?

    fun findByUsernameContainingIgnoreCase(username: String, pageable: Pageable): Page<Employee>

}