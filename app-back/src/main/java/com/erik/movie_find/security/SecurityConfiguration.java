package com.erik.movie_find.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // DEFINE A CLASSE COMO CONFIGURACAO
@EnableWebSecurity // ME PERMITE CONFIGURAR OS METODOS DE SEGURANCA DA APLICACAO
public class SecurityConfiguration {

    // VALIDA SE OS USUARIOS TEM PERMISSAO DE ACESSAR A APLICACAO
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

    }
}
