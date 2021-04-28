package com.example.youtube_clone.provider

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Component
class RequestProvider {
    private val logger: Logger = LoggerFactory.getLogger(RequestProvider::class.java)

    fun getIp(): String {
        val req = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        var ip = req.getHeader("X-FORWARDED-FOR")
        if (ip == null) {
            ip = req.remoteAddr
        }
        return ip
    }
}