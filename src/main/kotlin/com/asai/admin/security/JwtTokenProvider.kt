package com.asai.admin.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

    // 建議用 env 或 config 注入，不要硬編碼喔~
    private val secretKeyString = "60cc8ed871f0977119b2cee9930bc04edf9861304716b198464dfe2d8283f4f9" // 32字元以上
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKeyString.toByteArray(StandardCharsets.UTF_8))
    private val jwtParser: JwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build()

    private val expiration = 7 * 3600000L // 1小時，改成 Long 比較安全

    fun generateToken(username: String?): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()
    }

    fun getUsernameFromToken(token: String): String =
        jwtParser.parseClaimsJws(token).body.subject

    fun validateToken(token: String): Boolean =
        try {
            jwtParser.parseClaimsJws(token)
            true
        } catch (ex: SecurityException) {
            println("Invalid JWT signature.")
            false
        } catch (ex: MalformedJwtException) {
            println("Invalid JWT token.")
            false
        } catch (ex: ExpiredJwtException) {
            println("Expired JWT token.")
            false
        } catch (ex: UnsupportedJwtException) {
            println("Unsupported JWT token.")
            false
        } catch (ex: IllegalArgumentException) {
            println("JWT claims string is empty.")
            false
        }
}