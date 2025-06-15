package com.asai.admin

import com.asai.admin.repository.ArticleRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Test
    fun contextLoads() {
        println(articleRepository.findAll())
    }

}
