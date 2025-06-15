package com.asai.admin.service.impl

import com.asai.admin.dto.CreateClassificationRequest
import com.asai.admin.dto.CreateEmployeeRequest
import com.asai.admin.entity.Employee
import com.asai.admin.repository.EmployeeRepository
import com.asai.admin.service.EmployeeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository, private val passwordEncoder: PasswordEncoder): EmployeeService {

    @Transactional
    override fun findAll(username: String, pageable: Pageable): Page<Employee> {
        return employeeRepository.findByUsernameContainingIgnoreCase(username, pageable)
    }

    @Transactional
    override fun delete(id: Long) {
        return employeeRepository.deleteById(id)
    }

    @Transactional
    override fun modify(id: Long, update: Employee) {
        val existing = employeeRepository.findById(id).orElseThrow { RuntimeException("Employee not found") }
        existing.username = update.username
        if (!update.password.isNullOrBlank()) {
            if (update.password != existing.password) {
                if (!passwordEncoder.matches(update.password, existing.password)) {
                    existing.password = passwordEncoder.encode(update.password)
                }
            }
        }
        existing.state = update.state
        employeeRepository.save(existing)
    }

    @Transactional
    override fun create(request: CreateEmployeeRequest) {
        val newEmployee = Employee(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            state = request.state,
        )
        employeeRepository.save(newEmployee)
    }

    override fun deleteBatch(ids: List<Long>) {
        employeeRepository.deleteAllById(ids)
    }

}