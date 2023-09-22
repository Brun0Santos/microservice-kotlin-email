package com.email.microservice.mapper

import com.email.microservice.dtos.EmailDto
import com.email.microservice.model.EmailModel
import java.time.LocalDateTime

class ModelMapper {

    fun convertToDto(emailModel: EmailModel): EmailDto {
        return EmailDto(
            emailFrom = emailModel.emailFrom, emailTo = emailModel.emailTo,
            subject = emailModel.subject, text = emailModel.text, name = emailModel.name
        );
    }
}