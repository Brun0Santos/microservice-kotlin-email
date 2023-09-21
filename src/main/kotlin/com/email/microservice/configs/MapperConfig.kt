package com.email.microservice.configs

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapperConfig {

    @Bean
    public fun modelMapper(): ModelMapper {
        return ModelMapper();
    }
}