package com.email.microservice.controller

import com.email.microservice.dtos.EmailDto
import com.email.microservice.service.EmailService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class EmailController(private val emailService: EmailService) {

    @GetMapping("/status")
    fun hello(): String {
        return "Server is running..."
    }

    @PostMapping()
    fun sendingEmail(@RequestBody @Valid emailDto: EmailDto) {
        emailService.sendEmail(emailDto)
    }
}