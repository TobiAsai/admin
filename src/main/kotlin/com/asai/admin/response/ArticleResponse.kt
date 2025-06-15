package com.asai.admin.response

import com.asai.admin.entity.State
import java.time.LocalDate

data class ArticleResponse(
    val id: Long? = 0,
    var title: String? = null,
    var content: String? = null,
    var editor: String? = null,
    var classification: String? = null,
    var date: LocalDate = LocalDate.now(),
    var view: Long = 0,
    var state: State = State.active,
)
