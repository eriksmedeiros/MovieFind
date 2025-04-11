package com.erik.movie_find.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erik.movie_find.dtos.CommentDTO;
import com.erik.movie_find.services.CommentService;

@RestController
@RequestMapping("api/movies/{movieId}/comments")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable Long movieId, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(movieId, commentDTO));
    }
    
    @GetMapping
    public ResponseEntity<?> getComments(@PathVariable Long movieId) {
        return ResponseEntity.ok(commentService.getCommentsByMovie(movieId));
    }
}
