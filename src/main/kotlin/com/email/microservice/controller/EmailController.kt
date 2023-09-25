package com.email.microservice.controller

import com.email.microservice.dtos.EmailDto
import com.email.microservice.dtos.TokenDto
import com.email.microservice.service.EmailService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class EmailController(private val emailService: EmailService) {

    @GetMapping("/status")
    fun hello(): String {
        return "Server is running..."
    }

    @PostMapping("/send-email")
    fun sendingEmail(@RequestBody emailDto: EmailDto): ResponseEntity<HttpStatus> {
        return emailService.sendEmail(emailDto)
    }

    @PostMapping("/token/validate")
    fun validateToken(@RequestBody @Valid tokenDto: String): ResponseEntity<TokenDto> {
        return ResponseEntity.ok().body(emailService.validationToken(tokenDto))
    }
}
