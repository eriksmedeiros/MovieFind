package com.erik.movie_find.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> getMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.uploadMovie(movieDTO));
    }
        
}
