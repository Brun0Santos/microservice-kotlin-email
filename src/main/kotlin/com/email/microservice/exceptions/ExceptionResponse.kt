package com.email.microservice.exceptions

import java.util.*

data class ExceptionResponse(
    private val date: Date,
    private val details: String,
    private val message: String
) {
}