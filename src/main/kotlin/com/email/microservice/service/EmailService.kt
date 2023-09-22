package com.email.microservice.service

import com.email.microservice.configs.AuthConsumerFeign
import com.email.microservice.dtos.EmailDto
import com.email.microservice.dtos.TokenDto
import com.email.microservice.enums.StatusEmail
import com.email.microservice.model.EmailModel
import com.email.microservice.repository.EmailRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EmailService(
    private val emailRepository: EmailRepository,
    private val mapperConfig: ModelMapper,
    private val emailSender: JavaMailSender,
    private val authConsumerFeign: AuthConsumerFeign
) {

    fun sendEmail(emailDto: EmailDto): ResponseEntity<HttpStatus> {
        val emailModel = EmailModel()
        BeanUtils.copyProperties(emailDto, emailModel)
        emailModel.sendDataEmail = LocalDateTime.now()
        try {
            val message = SimpleMailMessage()
            message.setTo(emailModel.emailTo)
            message.setFrom(message.from)
            message.setSubject(emailModel.subject)
            message.setText(emailModel.text)
            emailSender.send(message)
            emailModel.statusEmail = StatusEmail.SEND
        } catch (e: MailException) {
            emailModel.statusEmail = StatusEmail.ERROR
        } finally {
            emailRepository.save(emailModel)
            return ResponseEntity.status(HttpStatus.CREATED).build()
        }
    }

    fun validationToken(token: TokenDto): TokenDto {
        try {
            return authConsumerFeign.validateToken(token)
        } catch (ex: Exception) {
            throw IllegalArgumentException("ERROR")
        }
    }
}