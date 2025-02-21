package com.erik.movie_find.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erik.movie_find.dtos.MovieDTO;
import com.erik.movie_find.models.Movie;
import com.erik.movie_find.repositorys.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieDTO uploadMovie(MovieDTO movieDTO) {
        Movie movie = movieDTO.toEntity();
        
        return movieRepository.save(movie).toDTO();
    }

    public MovieDTO checkMovie(String title) {
        String url = "https://api.themoviedb.org/3/account/21831135/".concat(title);
        RestTemplate restTemplate = new RestTemplate();
        MovieDTO movieDTO = restTemplate.getForObject(url, MovieDTO.class);
        return movieDTO;
    }
}
