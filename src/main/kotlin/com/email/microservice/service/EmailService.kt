package com.email.microservice.service

import com.email.microservice.dtos.EmailDto
import com.email.microservice.model.EmailModel
import com.email.microservice.repository.EmailRepository
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailRepository: EmailRepository,
    private val mapperConfig: ModelMapper
) {

    fun sendEmail(emailDto: EmailDto): ResponseEntity<HttpStatus> {
        val result = mapperConfig.map(emailDto, EmailModel::class)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}