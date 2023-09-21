package com.email.microservice.dtos

import jakarta.validation.constraints.Email
import org.jetbrains.annotations.NotNull

class EmailDto(
    @NotNull
    @Email
    val emailFrom: String,
    
    @Email
    @NotNull
    var emailTo: String,

    @NotNull
    var subject: String,

    @NotNull
    var text: String,
)