package com.example.youtube_clone.controller

import com.example.youtube_clone.domain.dto.LoginDTO
import com.example.youtube_clone.domain.entity.User
import com.example.youtube_clone.service.UserService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        val userService: UserService
) {

    @RequestMapping(
            path = ["/"],
            method = [RequestMethod.POST]
    )
    fun login(
            @RequestBody loginDTO: LoginDTO
    ): User {
        return userService.login(loginDTO)
    }
}