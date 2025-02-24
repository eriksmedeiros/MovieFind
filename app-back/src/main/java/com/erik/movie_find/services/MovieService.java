package com.erik.movie_find.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erik.movie_find.dtos.MovieDTO;
import com.erik.movie_find.models.Movie;
import com.erik.movie_find.repositorys.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Value("${tmdb.api.url}")
    private String API_URL;

    @Value("${tmdb.api.token}")
    private String API_TOKEN;

    @Value("${tmdb.api.key}")
    private String API_KEY;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieDTO uploadMovie(MovieDTO movieDTO) {
        Movie movie = movieDTO.toEntity();
        
        return movieRepository.save(movie).toDTO();
    }

    public String searchMovieByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();

        String url = API_URL + "?query=" + title + "&language=pt-BR&region-BR";
        String bearerToken = "Bearer " + API_TOKEN;

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            String.class
        );

        return response.getBody();
    }
}
