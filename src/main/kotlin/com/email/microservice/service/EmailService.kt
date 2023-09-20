package com.email.microservice.service

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class EmailService {

    @PostMapping
    fun hello (): String {
        return "Ola, bem vindo"
    }
}