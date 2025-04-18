package com.erik.movie_find.services;

import com.erik.movie_find.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// UserDetailsService -> IMPLEMENTA METODOS IMPORTANTES PARA O SERVIÇO DE AUTENTICACAO
public class AuthService implements UserDetailsService { // Implementa a interface usada pelo Spring Security

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco pelo email (username) e retorna para autenticação
        return userRepository.findByEmail(username);
    }
}
