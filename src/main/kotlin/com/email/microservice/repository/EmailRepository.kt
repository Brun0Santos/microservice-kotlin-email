package com.email.microservice.repository

import com.email.microservice.model.EmailModel
import org.springframework.data.mongodb.repository.MongoRepository

interface EmailRepository : MongoRepository<EmailModel, Int>