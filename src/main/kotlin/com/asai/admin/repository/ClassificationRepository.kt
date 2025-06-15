package com.asai.admin.repository

import com.asai.admin.entity.Classification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ClassificationRepository: JpaRepository<Classification, Long> {

    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Classification>

    fun findByName(name : String): Classification
}