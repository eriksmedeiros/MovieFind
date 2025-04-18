package com.erik.movie_find.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
