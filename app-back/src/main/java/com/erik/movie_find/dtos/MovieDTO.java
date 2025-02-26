package com.erik.movie_find.dtos;

import java.time.LocalDate;
import java.util.List;

import com.erik.movie_find.models.Movie;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {

    private String title; 
    private String overview;

    private String poster_path;

    private LocalDate release_date;

    private Double vote_average;

    private List<MovieDTO> results;

    public List<MovieDTO> getResults() {
        return results;
    }
    
    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setTitle(this.title);
        movie.setOverview(this.overview);
        movie.setPoster_path(this.poster_path);
        movie.setRelease_date(this.release_date);
        movie.setVote_average(this.vote_average);
        
        return movie;
    }
}