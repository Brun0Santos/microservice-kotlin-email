package com.email.microservice.model

import com.email.microservice.enums.StatusEmail
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "emails-service")
data class EmailModel(
    var name: String,
    var emailFrom: String,
    var emailTo: String,
    var subject: String,
    var text: String,
    var sendDataEmail: LocalDateTime,
    var statusEmail: StatusEmail,
) {
    constructor() : this(
        "", "", "", "", "", LocalDateTime.now(), StatusEmail.SEND
    )
}