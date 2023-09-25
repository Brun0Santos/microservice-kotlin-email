package com.email.microservice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
class SecurityConfiguration(private val authenticationFilter: AuthenticationFilter) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorize ->
                authorize.anyRequest()
                    .authenticated()
            }
            .cors { configs ->
                val configurations = CorsConfiguration().applyPermitDefaultValues()
                configurations.addAllowedMethod(HttpMethod.POST)
                configs.configurationSource(corsConfigurationSource(configurations))
            }
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    private fun corsConfigurationSource(configuration: CorsConfiguration): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}