package com.example.youtube_clone.domain.dto

data class ValidExceptionDTO(
        val errors: MutableSet<ValidErrorDTO>
)
