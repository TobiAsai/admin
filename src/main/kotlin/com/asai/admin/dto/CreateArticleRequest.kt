package com.asai.admin.dto

import com.asai.admin.entity.State
import java.time.LocalDate

data class CreateArticleRequest(
    val title: String,
    val content: String,
    val editor: String,
    val classification: String,
    val date: LocalDate,
    val view: Long,
    val state: State
)
