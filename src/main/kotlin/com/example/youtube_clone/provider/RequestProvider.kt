package com.example.youtube_clone.provider

import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Component
class RequestProvider {
    fun getIp(): String {
        val req = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        var ip = req.getHeader("X-FORWARDED-FOR")
        if (ip == null) {
            ip = req.remoteAddr
        }
        return ip
    }
}