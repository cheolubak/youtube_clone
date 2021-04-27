package com.example.youtube_clone.domain.dto

import com.sun.istack.NotNull
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class SignUpDTO(
        @field:NotNull
        @field:NotEmpty(
                message = "이메일은 필수입니다"
        )
        @field:Email(
                message = "이메일 형식이 잘못되었습니다",
        )
        private val email: String,

        @field:NotNull
        @field:NotEmpty(
                message = "비밀번호는 필수입니다"
        )
        @field:Pattern(
                regexp = "[0-9a-zA-Z!@#$%^&*()_\\-+=\\[\\]{};:\"|'?/><.,`~]{6,50}",
                message = "비밀번호는 숫자, 영어, 특수문자로 6자에서 50자까지 가능합니다"
        )
        private val password: String,

        @field:NotNull
        @field:NotEmpty(
                message = "닉네임은 필수입니다"
        )
        @field:Pattern(
                regexp = "[0-9a-zA-Z가-힣]{1,20}",
                message = "닉네임은 숫자, 영어, 한글로 1자에서 20자까지 가능합니다"
        )
        private val nickname: String,
        private val profile: String? = null,
) {
    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun getNickname(): String {
        return nickname
    }

    fun getProfile(): String? {
        return profile
    }
}
