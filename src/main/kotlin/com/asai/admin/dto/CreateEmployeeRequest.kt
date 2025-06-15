package com.asai.admin.dto

import com.asai.admin.entity.State

data class CreateEmployeeRequest(
    val username: String,
    val password: String,
    val state: State,
)
