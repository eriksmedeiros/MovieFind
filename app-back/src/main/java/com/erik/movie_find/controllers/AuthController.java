package com.erik.movie_find.controllers;

import com.erik.movie_find.dtos.AuthenticationDTO;
import com.erik.movie_find.dtos.LoginResponseDTO;
import com.erik.movie_find.dtos.UserDTO;
import com.erik.movie_find.models.User;
import com.erik.movie_find.repositorys.UserRepository;
import com.erik.movie_find.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController // Define a classe como um controller REST
@RequestMapping("/auth") // Mapeia as requisições para "/auth"
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; // Gerenciador de autenticação do Spring

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authDTO) {

        // Cria um token com email e senha vindos do DTO
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());

        // Realiza a autenticação usando o authenticationManager
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Gera um token JWT com os dados do usuário autenticado
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // Retorna 200 OK se autenticado com sucesso
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO){ // Metodo que recebe um objeto UserDTO no corpo da requisição, e o valida

        // Verifica se já existe um usuário com o mesmo email no banco
        if (this.userRepository.findByEmail(userDTO.getEmail()) != null) {
             return ResponseEntity.badRequest().build();
         }

        // Codifica a senha usando o algoritmo BCrypt para segurança
         String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());

        // Cria uma nova instância da entidade User com os dados do DTO e a senha já criptografada
        User newUser = new User(userDTO.getEmail(), encryptedPassword, userDTO.getImageUrl(), userDTO.getRole());

        // Salva o novo usuário no banco de dados
        this.userRepository.save(newUser);

        // Retorna uma resposta 200 (OK) com o novo usuário no corpo da resposta
         return ResponseEntity.ok(newUser);
    }
}
