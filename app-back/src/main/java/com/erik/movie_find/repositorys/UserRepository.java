package com.erik.movie_find.repositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.erik.movie_find.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // SEMPRE COLOCAR TIPO DE RETORNO COMO USERDETAILS
    UserDetails findByEmail(String email);
}
