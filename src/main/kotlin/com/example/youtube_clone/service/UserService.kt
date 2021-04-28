package com.example.youtube_clone.service

import com.example.youtube_clone.domain.dto.AccessTokenDTO
import com.example.youtube_clone.domain.dto.LoginDTO
import com.example.youtube_clone.domain.dto.SignUpDTO
import com.example.youtube_clone.domain.entity.AccessToken
import com.example.youtube_clone.domain.entity.Channel
import com.example.youtube_clone.domain.entity.User
import com.example.youtube_clone.domain.entity.UserRole
import com.example.youtube_clone.domain.enum.UserRoleType
import com.example.youtube_clone.provider.RequestProvider
import com.example.youtube_clone.provider.TokenProvider
import com.example.youtube_clone.repository.AccessTokenRepository
import com.example.youtube_clone.repository.ChannelRepository
import com.example.youtube_clone.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository,
        val channelRepository: ChannelRepository,
        val accessTokenRepository: AccessTokenRepository,
        val tokenProvider: TokenProvider,
        val requestProvider: RequestProvider
) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun login(
            loginDTO: LoginDTO,
            clientKey: String
    ): AccessTokenDTO {
        val email: String = loginDTO.getEmail()
        val password: String = loginDTO.getPassword()
        val findUser: Optional<User> = userRepository.findByEmail(email)
        val ip = requestProvider.getIp()

        if (findUser.isPresent) {
            val user: User = findUser.get()
            val matched = passwordEncoder.matches(password, user.getPassword())

            if (matched) {
                val token = tokenProvider.createToken(user)
                val accessToken = AccessToken(
                        token = token,
                        clientKey = clientKey,
                        ip = ip,
                        user = user,
                        expiredAt = LocalDateTime.now().plusDays(1)
                )
                accessTokenRepository.save(accessToken)
                return AccessTokenDTO(token = accessToken.getToken())
            } else throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    fun signUp(
            signUpDTO: SignUpDTO,
            clientKey: String
    ): AccessTokenDTO {
        val email: String = signUpDTO.getEmail()
        var password: String = signUpDTO.getPassword()
        var nickname: String = signUpDTO.getNickname()
        var profile: String? = signUpDTO.getProfile()
        val ip = requestProvider.getIp()

        val user = User(
                email = email,
                password = passwordEncoder.encode(password),
                nickname = nickname,
                profile = profile
        )
        userRepository.save(user)

        val token = tokenProvider.createToken(user)

        val accessToken = AccessToken(
                token = token,
                clientKey = clientKey,
                ip = ip,
                user = user,
                expiredAt = LocalDateTime.now().plusDays(1)
        )
        accessTokenRepository.save(accessToken)

        val role = UserRole(
                user = user,
                role = UserRoleType.USER
        )
        user.setRole(role)

        val channel = Channel(
                name = nickname,
                user = user
        )
        channelRepository.save(channel)

        return AccessTokenDTO(token = accessToken.getToken())
    }
}
