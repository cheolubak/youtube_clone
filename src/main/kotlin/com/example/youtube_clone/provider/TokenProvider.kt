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
import org.springframework.security.core.Authentication
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
        private val tokenValiditySecond: Int
) : InitializingBean {
    private val logger: Logger = LoggerFactory.getLogger(TokenProvider::class.java)
    private val AUTHORITIES_KEY = "auth"
    private lateinit var key: Key

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.getNickname())
        claims.put("userId", user.getId())
        claims.put("createdAt", user.getCreatedAt())
        val now = Date().time
        val validity = Date(now + (tokenValiditySecond * 1000))

        return Jwts.builder()
                .setSubject(user.getNickname())
                .claim(AUTHORITIES_KEY, claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is io.jsonwebtoken.security.SecurityException,
                is MalformedJwtException -> {
                    logger.error("????????? ???????????????")
                }
                is UnsupportedJwtException -> {
                    logger.error("???????????? ?????? ???????????????")
                }
                is IllegalArgumentException -> {
                    logger.error("????????? ???????????????")
                }
            }
        }
        return false
    }
}