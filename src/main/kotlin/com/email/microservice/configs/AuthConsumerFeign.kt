package com.email.microservice.configs

import com.email.microservice.dtos.TokenDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Configuration
@FeignClient(name = "\${app.auth.name}", url = "\${app.auth.url}")
interface AuthConsumerFeign {

    @PostMapping("/api/token/validate")
    fun validateToken(@RequestBody token: TokenDto): TokenDto
}