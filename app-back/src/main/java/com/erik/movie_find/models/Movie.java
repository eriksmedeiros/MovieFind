package com.erik.movie_find.models;

import java.time.LocalDate;
import java.util.UUID;

import com.erik.movie_find.dtos.MovieDTO;

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

    private String overview;

    private String poster_path;

    private LocalDate release_date;

    private Double vote_average;

    public Movie(String title, String overview, String poster_path, LocalDate release_date, Double vote_average) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(this.title);
        movieDTO.setOverview(this.overview);
        movieDTO.setPoster_path(this.poster_path);
        movieDTO.setRelease_date(this.release_date);
        movieDTO.setVote_average(this.vote_average);

        return movieDTO;
    }
}
