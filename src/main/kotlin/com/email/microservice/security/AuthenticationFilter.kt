package com.email.microservice.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import com.email.microservice.configs.AuthConsumerFeign
import com.email.microservice.exceptions.InvalidJwtAuthException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Service
class AuthenticationFilter(private val httpClient: AuthConsumerFeign, private var secretKey: String = "segredinho") :
    OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = extractToken(request)
        if (token != null && validateToken(token)) {
            val username = decodeJwt(token).signature
            SecurityContextHolder.getContext()
                .authentication = UsernamePasswordAuthenticationToken(username, null, emptyList())
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? =
        request.getHeader("Authorization")?.removePrefix("Bearer ")

    private fun validateToken(token: String): Boolean {
        try {
            httpClient.validateToken(token)
            return true
        } catch (ex: Exception) {
            throw InvalidJwtAuthException("Token invalid")
        }
    }

    private fun decodeJwt(token: String): DecodedJWT {
        val bae64Secret = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        val alg = Algorithm.HMAC256(bae64Secret.toByteArray())
        val verifier: JWTVerifier = JWT.require(alg).build()
        return verifier.verify(token)
    }
}