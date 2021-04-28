package com.example.youtube_clone.controller

import com.example.youtube_clone.domain.dto.LoginDTO
import com.example.youtube_clone.domain.dto.SignUpDTO
import com.example.youtube_clone.domain.entity.User
import com.example.youtube_clone.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(
        value = ["user"]
)
class UserController(
        val userService: UserService
) {

    @RequestMapping(
            value = ["sign-up"],
            method = [RequestMethod.POST]
    )
    fun signup(
            @RequestHeader("CLIENT_KEY") clientKey: String,
            @RequestBody @Valid signUpDTO: SignUpDTO
    ): ResponseEntity<String> {
        val accessToken = userService.signUp(signUpDTO, clientKey)
        return ResponseEntity(accessToken, HttpStatus.CREATED)
    }

    @RequestMapping(
            value = [""],
            method = [RequestMethod.POST]
    )
    fun login(
            @RequestBody @Valid loginDTO: LoginDTO
    ): ResponseEntity<User> {
        val user = userService.login(loginDTO)
        return ResponseEntity(user, HttpStatus.OK)
    }
}
