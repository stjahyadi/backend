package com.seanyoung.backend.common.exception

import com.seanyoung.backend.common.ProblemLogger.Companion.log
import com.seanyoung.backend.common.log
import org.springframework.data.relational.core.conversion.DbActionExecutionException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.URI

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleHttpServerError(exception: DbActionExecutionException): Problem {
        return Problem(
            type = URI.create("/problems/internal-server-error"),
            title = "An unhandled exception occurred.",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            detail = exception.message
        ).also { it.log() }
    }
}
