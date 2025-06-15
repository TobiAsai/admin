package com.asai.admin.service

import com.asai.admin.dto.CreateClassificationRequest
import com.asai.admin.entity.Classification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ClassificationService {
    fun findAll(name: String, pageable: Pageable): Page<Classification>

    fun delete(id: Long)

    fun modify(id: Long, update: Classification)

    fun create(request: CreateClassificationRequest)

    fun deleteBatch(ids: List<Long>)
}