package com.erik.movie_find.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.erik.movie_find.dtos.MovieDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String genre;

    private String director;

    private String synopsis;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime duration;

    private String imageUrl;

    public Movie(String title, String genre, String director, String synopsis, LocalDate releaseDate,
            LocalTime duration, String imageUrl) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.imageUrl = imageUrl;
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(this.title);
        movieDTO.setGenre(this.genre);
        movieDTO.setDirector(this.director);
        movieDTO.setSynopsis(this.synopsis);
        movieDTO.setReleaseDate(this.releaseDate);
        movieDTO.setDuration(this.duration);
        movieDTO.setImageUrl(this.imageUrl);
        return movieDTO;
    }
}
