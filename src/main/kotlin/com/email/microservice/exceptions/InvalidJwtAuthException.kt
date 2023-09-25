package com.email.microservice.exceptions

class InvalidJwtAuthException(message: String) : RuntimeException(message = message) {
}