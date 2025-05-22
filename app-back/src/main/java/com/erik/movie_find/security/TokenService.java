package com.erik.movie_find.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.erik.movie_find.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service // Define a classe como um serviço gerenciado pelo Spring
public class TokenService {
    @Value("${api.security.token.secret}") // Injeta a chave secreta definida no application.properties
    private String secretKey;

    public String generateToken(User user){
        try {
            // Define o algoritmo de assinatura HMAC usando a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            // Cria o token com emissor, assunto (email), expiração e assinatura
            String token = JWT.create()
                    .withIssuer("auth-api") // Identificador da API emissora
                    .withSubject(user.getEmail()) // Email do usuário será o "subject"
                    .withExpiresAt(genExpirationDate()) // Data de expiração
                    .sign(algorithm); // Assina o token

            return token; // Retorna o token gerado
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey); // Usa a mesma chave para validação
            return JWT.require(algorithm)
                    .withIssuer("auth-api") // Verifica se o token é da API correta
                    .build()
                    .verify(token)// Valida o token
                    .getSubject(); // Retorna o email (subject) do token
        } catch (JWTVerificationException exception) {
             return ""; // Token inválido ou expirado
        }
    }

    // Define a expiração do token para 2 horas a partir de agora
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
