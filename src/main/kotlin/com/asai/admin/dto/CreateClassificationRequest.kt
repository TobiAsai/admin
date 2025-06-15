package com.asai.admin.dto

import com.asai.admin.entity.State

data class CreateClassificationRequest(
    val name: String,
    val state: State,
)
