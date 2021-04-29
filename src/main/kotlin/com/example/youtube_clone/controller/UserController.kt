package com.example.youtube_clone.controller

import com.example.youtube_clone.domain.dto.AccessTokenDTO
import com.example.youtube_clone.domain.dto.LoginDTO
import com.example.youtube_clone.domain.dto.SignUpDTO
import com.example.youtube_clone.domain.dto.UserDTO
import com.example.youtube_clone.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
  @ApiOperation(value = "회원가입")
  @PostMapping("sign-up")
  fun signup(
      @RequestHeader("CLIENT_KEY") clientKey: String,
      @RequestBody @Valid signUpDTO: SignUpDTO
  ): ResponseEntity<AccessTokenDTO> {
    val accessToken = userService.signUp(signUpDTO, clientKey)
    return ResponseEntity(accessToken, HttpStatus.CREATED)
  }

  @ApiOperation(value = "로그인")
  @PostMapping("")
  fun login(
      @RequestHeader("CLIENT_KEY") clientKey: String,
      @RequestBody @Valid loginDTO: LoginDTO
  ): ResponseEntity<AccessTokenDTO> {
    val user = userService.login(loginDTO, clientKey)
    return ResponseEntity(user, HttpStatus.OK)
  }

  @ApiOperation(value = "내정보 불러오기")
  @GetMapping("")
  fun getInfo(
      @RequestHeader("CLIENT_KEY") clientKey: String,
      @RequestHeader("Authentication") accessToken: String
  ): ResponseEntity<UserDTO> {
    val user = userService.getInfo(clientKey, accessToken)
    return ResponseEntity(user, HttpStatus.OK)
  }
}
