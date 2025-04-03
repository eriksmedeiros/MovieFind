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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieService {
    
    @Autowired
    private final MovieRepository movieRepository;

    @Value("${tmdb.api.url}")
    private String API_URL;

    @Value("${tmdb.api.token}")
    private String API_TOKEN;

    @Value("${tmdb.api.key}")
    private String API_KEY;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAllMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=pt-BR";
        String bearerToken = "Bearer " + API_TOKEN;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieDTO> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            MovieDTO.class
        );

        return response.getBody().getResults();
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

    public Movie getMovieById(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = API_URL + id + "?api_key=" + API_TOKEN + "&language=pt-BR";
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

        String responseBody = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        
        try{
            return objectMapper.readValue(responseBody, Movie.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar JSON para Movie", e);
        }
    }
}
