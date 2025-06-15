package com.asai.admin.service

import com.asai.admin.dto.CreateEmployeeRequest
import com.asai.admin.entity.Employee
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface EmployeeService {

    fun findAll(username: String, pageable: Pageable): Page<Employee>

    fun delete(id: Long)

    fun modify(id: Long, update: Employee)

    fun create(request: CreateEmployeeRequest)
}