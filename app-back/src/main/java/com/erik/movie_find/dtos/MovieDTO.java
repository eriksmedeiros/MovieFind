package com.erik.movie_find.dtos;

import java.util.Date;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {

    private Long id;
    
    private String title; 
    private String overview;

    private String poster_path;

    private Date release_date;

    private Double vote_average;

    private List<MovieDTO> results;

    public List<MovieDTO> getResults() {
        return results;
    }
    
}