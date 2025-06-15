package com.asai.admin.response

import com.asai.admin.entity.State

data class ClassificationResponse(
    var id: Long,
    var name: String?,
    var state: State = State.active,
)
