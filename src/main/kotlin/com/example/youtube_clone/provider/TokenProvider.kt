package com.example.youtube_clone.provider

import com.example.youtube_clone.domain.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class TokenProvider(
        @Value("\${jwt.secret}")
        private val secret: String,
        @Value("\${jwt.token-validity-in-seconds}")
        private val tokenValiditySecond: Int,
) : InitializingBean {
    private val logger: Logger = LoggerFactory.getLogger(TokenProvider::class.java)
    private val AUTHORITIES_KEY = "auth"
    private var key: Key = TODO()

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createToken(user: User): String {
        val temp = StringBuilder();
        temp.append(user.getId())
        temp.append(user.getNickname())
        temp.append(user.getUpdatedAt())
        val now = Date().time
        val validity = Date(now + (tokenValiditySecond * 1000))

        return Jwts.builder()
                .setSubject(user.getNickname())
                .claim(AUTHORITIES_KEY, temp.toString())
                .signWith(key, SignatureAlgorithm.ES512)
                .setExpiration(validity)
                .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: io.jsonwebtoken.security.SecurityException) {
            logger.error("잘못된 서명입니다")
        } catch (e: MalformedJwtException) {
            logger.error("잘못된 서명입니다")
        } catch (e: UnsupportedJwtException) {
            logger.error("지원하지 않는 토큰입니다")
        } catch (e: IllegalArgumentException) {
            logger.error("잘못된 토큰입니다")
        }
        return false
    }
}