package com.asai.admin.config

import com.asai.admin.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val jwtFilter: JwtAuthenticationFilter
) {

@Bean
fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http.csrf { it.disable() }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .authorizeHttpRequests {
            it.requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
        }
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        .cors { // Kotlin 的 Lambda 表達式，可以直接使用 `it` 或省略參數名
        val corsConfiguration = CorsConfiguration().apply { // 使用 Kotlin 的 `apply` 函式，讓設定更流暢
            addAllowedOrigin("http://localhost:5173/")
            allowCredentials = true // Kotlin 的屬性賦值
            addAllowedHeader("*")
            addAllowedMethod("*")
            addExposedHeader("*")
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        // 這裡的 `it` 代表 `CorsConfigurer` 物件
        it.configurationSource(source)
    }
    return http.build()
}

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}