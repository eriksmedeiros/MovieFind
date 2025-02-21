package com.erik.movie_find.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.erik.movie_find.models.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {

    String title; 
    String genre; 
    String director; 
    String synopsis;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate releaseDate;

    @JsonFormat(pattern = "HH:mm")
    LocalTime duration;

    String imageUrl;
    
    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setTitle(this.title);
        movie.setGenre(this.genre);
        movie.setDirector(this.director);
        movie.setSynopsis(this.synopsis);
        movie.setReleaseDate(this.releaseDate);
        movie.setDuration(this.duration);
        movie.setImageUrl(this.imageUrl);
        return movie;
    }
}