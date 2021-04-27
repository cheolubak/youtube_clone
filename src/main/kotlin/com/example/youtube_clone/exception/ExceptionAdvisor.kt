package com.example.youtube_clone.exception

import com.example.youtube_clone.domain.dto.ValidErrorDTO
import com.example.youtube_clone.domain.dto.ValidExceptionDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class ExceptionAdvisor {
    val logger: Logger = LoggerFactory.getLogger(ExceptionAdvisor::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun processValidationError(
            exception: MethodArgumentNotValidException
    ): ValidExceptionDTO {
        val bindingResult: BindingResult = exception.bindingResult
        val errors: MutableSet<ValidErrorDTO> = mutableSetOf()
        logger.error("errors : " + bindingResult.fieldErrors.size)
        for (filedError in bindingResult.fieldErrors) {
            logger.error("key : " + filedError.field + " message : " + filedError.defaultMessage)
            val validException = ValidErrorDTO(
                    key = filedError.field,
                    message = filedError.defaultMessage
            )
            errors.add(validException)
        }
        val validException = ValidExceptionDTO(errors = errors)
        logger.error(validException.toString())
        return validException
    }
}
