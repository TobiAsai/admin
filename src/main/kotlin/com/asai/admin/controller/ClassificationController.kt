package com.asai.admin.controller

import com.asai.admin.dto.CreateClassificationRequest
import com.asai.admin.entity.Classification
import com.asai.admin.response.ClassificationResponse
import com.asai.admin.response.CreateResponse
import com.asai.admin.response.DeleteResponse
import com.asai.admin.response.EntityResponse
import com.asai.admin.response.PutResponse
import com.asai.admin.service.ClassificationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/classification")
class ClassificationController(
    private val classificationService: ClassificationService
) {

    @GetMapping("/findAll")
    fun findAll(
        @RequestParam(defaultValue = "") name: String,
        pageable: Pageable
    ): EntityResponse<Page<ClassificationResponse>> {
        val result = classificationService.findAll(name, pageable).map {
            ClassificationResponse(
                it.id,
                it.name,
                it.state
            )
        }
        return EntityResponse(200, result, "Classification page result")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): DeleteResponse {
        classificationService.delete(id)
        return DeleteResponse(200, "Classification deleted successfully")
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody update: Classification): PutResponse {
        classificationService.modify(id, update)
        return PutResponse(code = 200, message = "Classification updated successfully")
    }

    @PostMapping("/create")
    fun create(@RequestBody request: CreateClassificationRequest): CreateResponse {
        val classification = classificationService.create(request)
        return CreateResponse(code = 200, message = "Classification created successfully", data = classification)
    }

    @DeleteMapping("/deleteBatch")
    fun deleteBatch(@RequestBody ids: List<Long>): DeleteResponse {
        classificationService.deleteBatch(ids)
        return DeleteResponse(code = 200, message = "Classification deleted successfully")
    }
}