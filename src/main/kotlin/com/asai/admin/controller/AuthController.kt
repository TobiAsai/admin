package com.asai.admin.controller

import com.asai.admin.dto.LoginRequest
import com.asai.admin.response.LoginResponse
import com.asai.admin.dto.RegisterRequest
import com.asai.admin.response.RegisterResponse
import com.asai.admin.entity.Employee
import com.asai.admin.repository.EmployeeRepository
import com.asai.admin.security.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val employeeRepository: EmployeeRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtTokenProvider
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val user = employeeRepository.findByUsername(request.username)
            ?: return LoginResponse(message = "使用者不存在", code = 400)

        if (!passwordEncoder.matches(request.password, user.password)) {
            return LoginResponse(message = "密碼錯誤", code = 400)
        }

        val token = jwtProvider.generateToken(user.username)
        return LoginResponse(token, code = 200)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): RegisterResponse {
        if (employeeRepository.findByUsername(request.username) != null) {
            return RegisterResponse(400, "false")
        }

        val employee = Employee(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            state = request.state,
        )

        employeeRepository.save(employee)
        return RegisterResponse(200, "true")
    }
}