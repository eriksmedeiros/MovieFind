package com.erik.movie_find.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erik.movie_find.dtos.MovieDTO;
import com.erik.movie_find.models.Movie;
import com.erik.movie_find.reepositorys.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie uploadMovie(MovieDTO movieDTO) {
        Movie movie = new Movie(movieDTO.title(), movieDTO.genre(), movieDTO.director(), movieDTO.synopsis(), movieDTO.releaseDate(), movieDTO.duration(), movieDTO.imageUrl());
        return movieRepository.save(movie);
    }
}
