package com.asai.admin.repository

import com.asai.admin.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByTitleContainingIgnoreCase(name: String, pageable: Pageable): Page<Article>
}