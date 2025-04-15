package com.erik.movie_find.models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.erik.movie_find.dtos.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
// USERDETAILS IMPLEMENTA METODOS PARA AUTENTICACAO DE USUARIOS
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;
    private String password;
    private String urlImage;
    private UserRole role;

    // metodo para retornar as roles (permissoes) do usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE ADMIN TEM PERMISSOES DE USUARIO E ADMIN
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        // ROLE USER TEM PERSMISSÕES APENAS DE USUARIO
        } else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
