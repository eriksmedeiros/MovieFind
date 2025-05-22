package com.erik.movie_find.security;

import com.erik.movie_find.repositorys.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Define a classe como um componente gerenciado pelo Spring
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService; // Injeta o serviço responsável pela geração/validação de tokens

    @Autowired
    UserRepository userRepository; // Injeta o repositório de usuários

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request); // Recupera o token do cabeçalho da requisição

        if (token != null) {
            var email = tokenService.validateToken(token); // Valida o token e extrai o email
            UserDetails user = userRepository.findByEmail(email); // Busca o usuário no banco

            // Cria uma autenticação com base no usuário e insere no contexto de segurança
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }

    // Recupera o token do cabeçalho Authorization
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        // Remove o prefixo "Bearer " do token
        return authHeader.replace("Bearer ", "");
    }
}
