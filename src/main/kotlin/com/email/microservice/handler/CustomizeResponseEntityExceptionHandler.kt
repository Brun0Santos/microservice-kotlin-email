package com.email.microservice.handler

import com.email.microservice.exceptions.ExceptionResponse
import com.email.microservice.exceptions.InvalidJwtAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import java.util.*

@RestController
@ControllerAdvice
class CustomizeResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidJwtAuthException::class)
    fun invalidToken(e: InvalidJwtAuthException, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(Date(), e.message.toString(), request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}