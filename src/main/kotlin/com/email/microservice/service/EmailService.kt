package com.email.microservice.service

import org.springframework.stereotype.Service

@Service
class EmailService {

    fun hello(): String {
        return "Ola, bem vindo"
    }
}