package com.erik.movie_find.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erik.movie_find.dtos.MovieDTO;
import com.erik.movie_find.services.MovieService;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> getMovies(){
        return movieService.getAllMovies();
    }
        
    @GetMapping("/search")
    public ResponseEntity<?> searchMovie(@RequestParam String title){
        String newTitle = title.replace(" ", "%20");
        return ResponseEntity.ok(movieService.searchMovieByTitle(newTitle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
