package com.asai.admin.entity

import com.asai.admin.entity.State
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "table_article")
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    var title: String? = null,
    @Column
    var content: String? = null,
    @Column
    var editor: String? = null,
    @Column
    var classification: String? = null,
    @Column
    var date: LocalDate = LocalDate.now(),
    @Column
    var view: Long = 0,
    @Column
    @Enumerated(EnumType.STRING)
    var state: State = State.active,

    @ManyToOne
    @JoinColumn(name = "classification_id")
    var classificationClass: Classification? = null,
)
