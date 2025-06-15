package com.asai.admin.service.impl

import com.asai.admin.dto.CreateArticleRequest
import com.asai.admin.entity.Article
import com.asai.admin.repository.ArticleRepository
import com.asai.admin.repository.ClassificationRepository
import com.asai.admin.service.ArticleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleServiceImpl(private val articleRepository: ArticleRepository, private val classificationRepository: ClassificationRepository) : ArticleService {
    @Transactional
    override fun findAll(title: String, pageable: Pageable): Page<Article> {
        return articleRepository.findByTitleContainingIgnoreCase(title, pageable)
    }

    @Transactional
    override fun delete(id: Long) {
        articleRepository.deleteById(id)
    }

    @Transactional
    override fun modify(id: Long, update: Article) {
        val existing = articleRepository.findById(id).orElseThrow { RuntimeException("Classification not found") }
        val newClassification = classificationRepository.findByName(update.classification!!)
        existing.title = update.title
        existing.classification = newClassification.name
        existing.editor = update.editor
        existing.content = update.content
        existing.view = update.view
        existing.state = update.state
        existing.classificationClass = newClassification
        articleRepository.save(existing)
    }

    override fun create(request: CreateArticleRequest) {

        val newClassification = classificationRepository.findByName(request.classification)

        val newArticle = Article(
            title = request.title,
            content = request.content,
            editor = request.editor,
            view = request.view,
            state = request.state,
            classification = newClassification.name,
            date = request.date ,
            classificationClass = newClassification,
        )

        articleRepository.save(newArticle)
    }
}