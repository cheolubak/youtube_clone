package com.example.youtube_clone.service

import com.example.youtube_clone.domain.dto.LoginDTO
import com.example.youtube_clone.domain.dto.SignUpDTO
import com.example.youtube_clone.domain.entity.User
import com.example.youtube_clone.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun login(loginDTO: LoginDTO): User {
        val email: String = loginDTO.getEmail()
        val password: String = loginDTO.getPassword()
        val findUser: Optional<User> = userRepository.findByEmail(email)

        if (findUser.isPresent) {
            val user: User = findUser.get()
            val matched = passwordEncoder.matches(password, user.getPassword())

            if (matched) return user
            else throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    fun signUp(signUpDTO: SignUpDTO): User {
        val email: String = signUpDTO.getEmail()
        var password: String = signUpDTO.getPassword()
        var nickname: String = signUpDTO.getNickname()
        var profile: String? = signUpDTO.getProfile()

        val user = User(
                email = email,
                password = passwordEncoder.encode(password),
                nickname = nickname,
                profile = profile
        )
        userRepository.save(user)
        return user
    }
}
