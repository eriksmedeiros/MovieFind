package com.erik.movie_find.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record MovieDTO(
    String title, 
    String genre, 
    String director, 
    String synopsis,     
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate releaseDate, 
    @JsonFormat(pattern = "HH:mm")
    LocalTime duration, 
    String imageUrl) {
    
}
