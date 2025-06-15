package com.asai.admin.response

data class EntityResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
)