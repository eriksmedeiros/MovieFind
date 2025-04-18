package com.erik.movie_find.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // DEFINE A CLASSE COMO CONFIGURACAO
@EnableWebSecurity // ME PERMITE CONFIGURAR OS METODOS DE SEGURANCA DA APLICACAO
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    // VALIDA SE OS USUARIOS TEM PERMISSAO DE ACESSAR A APLICACAO
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // // DESABILITA PROTEÇÃO CSRF (recomendado para APIs stateless)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // DEFINE AUTENTICAÇÃO COMO STATELESS (sem sessão)
                .authorizeHttpRequests(authorize -> authorize // CONFIGURA AUTORIZAÇÕES PARA REQUISIÇÕES
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Liberado qualquer requisição para se fazer login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Liberado qualquer requisição para se registrar
                        .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN") // Somente ADMIN pode fazer POST em /movies
                        .anyRequest().authenticated() // Qualquer outra requisição precisa de autenticação
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // Constrói e retorna o SecurityFilterChain
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Retorna o gerenciador de autenticação com base na configuração
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // Codificador de senha usando o algoritmo BCrypt
        return new BCryptPasswordEncoder();
    }
}
