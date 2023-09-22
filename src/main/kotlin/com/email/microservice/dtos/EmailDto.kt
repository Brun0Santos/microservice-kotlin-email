package com.email.microservice.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

class EmailDto(
    @NotBlank
    val name: String,

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