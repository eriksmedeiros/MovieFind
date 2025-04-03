package com.erik.movie_find.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.erik.movie_find.services.MovieService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String text;
    private String author;

    private Long movieId;
    
    private LocalDateTime createdAt;

    public Comment(String text, String author, Long movieId) {
        this.text = text;
        this.author = author;
        this.movieId = movieId;
        this.createdAt = LocalDateTime.now();
    }
}
