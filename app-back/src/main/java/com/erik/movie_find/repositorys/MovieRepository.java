package com.erik.movie_find.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.movie_find.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    
    Movie findByTitle(String title);
}
