package com.email.microservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MicroserviceApplication

fun main(args: Array<String>) {
	runApplication<MicroserviceApplication>(*args)
}
