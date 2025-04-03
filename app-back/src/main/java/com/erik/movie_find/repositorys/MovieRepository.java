package com.erik.movie_find.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.movie_find.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
   
}
