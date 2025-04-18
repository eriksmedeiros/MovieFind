package com.erik.movie_find.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erik.movie_find.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
   
}
