package com.asai.admin.service.impl

import com.asai.admin.dto.CreateClassificationRequest
import com.asai.admin.entity.Classification
import com.asai.admin.repository.ClassificationRepository
import com.asai.admin.service.ClassificationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClassificationServiceImpl(private val classificationRepository: ClassificationRepository): ClassificationService {
    @Transactional
    override fun findAll(name: String, pageable: Pageable): Page<Classification> {
        return classificationRepository.findByNameContainingIgnoreCase(name, pageable)
    }

    @Transactional
    override fun delete(id: Long) {
        return classificationRepository.deleteById(id)
    }

    @Transactional
    override fun modify(id: Long, update: Classification) {
        val existing = classificationRepository.findById(id).orElseThrow { RuntimeException("Classification not found") }
        existing.name = update.name
        existing.state = update.state
        classificationRepository.save(existing)
    }

    override fun create(request: CreateClassificationRequest) {
        val newClass = Classification(
            name = request.name,
            state = request.state,
        )
        classificationRepository.save(newClass)
    }
}