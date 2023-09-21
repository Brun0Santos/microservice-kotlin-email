package com.email.microservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class EmailController {

    @GetMapping("/status")
    fun hello(): String {
        return "Server is running..."
    }
}