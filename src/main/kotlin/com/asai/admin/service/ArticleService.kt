package com.asai.admin.service

import com.asai.admin.dto.CreateArticleRequest
import com.asai.admin.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService {
    fun findAll(title: String, pageable: Pageable): Page<Article>

    fun delete(id: Long)

    fun modify(id: Long, update: Article)

    fun create(request: CreateArticleRequest)

    fun deleteBatch(ids: List<Long>)
}