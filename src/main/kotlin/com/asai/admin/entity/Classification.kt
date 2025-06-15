package com.asai.admin.entity

import com.asai.admin.entity.State
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "table_classification")
data class Classification(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    var name: String? = null,
    @Column
    @Enumerated(EnumType.STRING)
    var state: State = State.active,

    @OneToMany(mappedBy = "classification", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var articles: MutableList<Article> = mutableListOf()
)
