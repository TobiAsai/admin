package com.asai.admin.response

data class LoginResponse(
    val token: String? = null,
    val message: String? = null,
    val code: Int,
)