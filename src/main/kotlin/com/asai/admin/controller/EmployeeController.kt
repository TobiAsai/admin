package com.asai.admin.controller

import com.asai.admin.dto.CreateEmployeeRequest
import com.asai.admin.entity.Employee
import com.asai.admin.response.CreateResponse
import com.asai.admin.response.DeleteResponse
import com.asai.admin.response.EntityResponse
import com.asai.admin.response.PutResponse
import com.asai.admin.service.EmployeeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping("/findAll")
    fun findAll(
        @RequestParam(defaultValue = "") username: String,
        pageable: Pageable
    ): EntityResponse<Page<Employee>> {
        val result = employeeService.findAll(username, pageable)
        return EntityResponse(200, result, "Employee page result")
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id: Long): DeleteResponse {
        employeeService.delete(id)
        return DeleteResponse(200, "Employee deleted successfully")
    }

    @PutMapping("/update/{id}")
    fun saveEmployee(@PathVariable id: Long, @RequestBody update: Employee): PutResponse {
        employeeService.modify(id, update)
        return PutResponse(code = 200, message = "Employee updated successfully")
    }

    @PostMapping("/create")
    fun createEmployee(@RequestBody request: CreateEmployeeRequest): CreateResponse {
        val employee = employeeService.create(request)
        return CreateResponse(code = 200, message = "Employee created successfully", data = employee)
    }
}