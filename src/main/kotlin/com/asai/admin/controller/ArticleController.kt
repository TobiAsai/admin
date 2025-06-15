package com.asai.admin.controller

import com.asai.admin.dto.CreateArticleRequest
import com.asai.admin.entity.Article
import com.asai.admin.response.ArticleResponse
import com.asai.admin.response.CreateResponse
import com.asai.admin.response.DeleteResponse
import com.asai.admin.response.EntityResponse
import com.asai.admin.response.PutResponse
import com.asai.admin.service.ArticleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/article")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping("/findAll")
    fun findAll(
        @RequestParam(defaultValue = "") title: String,
        pageable: Pageable
    ): EntityResponse<Page<ArticleResponse>> {
        val article = articleService.findAll(title, pageable)
        val result = article.map { article ->
            ArticleResponse(
                id = article.id,
                title = article.title,
                content = article.content,
                editor = article.editor,
                classification = article.classificationClass?.name, // 取出分類名稱
                date = article.date,
                view = article.view,
                state = article.state
            )
        }
        return EntityResponse(200, result, "Classification page result")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): DeleteResponse {
        articleService.delete(id)
        return DeleteResponse(200, "Deleted successfully")
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody update: Article): PutResponse {
        articleService.modify(id, update)
        return PutResponse(code = 200, message = "Updated successfully")
    }

    @PostMapping("/create")
    fun create(@RequestBody request: CreateArticleRequest): CreateResponse {
        articleService.create(request)
        return CreateResponse(code = 200, message = "Created successfully", data = request)
    }
}