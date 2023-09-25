package com.email.microservice.service

import com.email.microservice.configs.AuthConsumerFeign
import com.email.microservice.dtos.EmailDto
import com.email.microservice.dtos.TokenDto
import com.email.microservice.enums.StatusEmail
import com.email.microservice.model.EmailModel
import com.email.microservice.repository.EmailRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailRepository: EmailRepository,
    private val emailSender: JavaMailSender,
    private val authConsumerFeign: AuthConsumerFeign
) {
    fun sendEmail(emailDto: EmailDto): ResponseEntity<HttpStatus> {
        val emailModel = EmailModel().apply {
            this.emailTo = emailDto.emailTo
            this.emailFrom = emailDto.emailFrom
            this.subject = emailDto.subject
            this.text = emailDto.text
            this.statusEmail = StatusEmail.SEND
        }
        try {
            val message = SimpleMailMessage().apply {
                this.setTo(emailModel.emailTo)
                this.from = emailModel.emailFrom
                this.subject = emailModel.subject
                this.text = emailModel.text
            }
            emailSender.send(message)
        } catch (e: MailException) {
            emailModel.statusEmail = StatusEmail.ERROR
        } finally {
            emailRepository.save(emailModel)
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    fun validationToken(token: String): TokenDto {
        try {
            return authConsumerFeign.validateToken(token)
        } catch (ex: Exception) {
            throw IllegalArgumentException("ERROR")
        }
    }
}