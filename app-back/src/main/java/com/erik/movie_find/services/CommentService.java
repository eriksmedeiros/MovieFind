package com.erik.movie_find.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.erik.movie_find.dtos.CommentDTO;
import com.erik.movie_find.models.Comment;
import com.erik.movie_find.models.Movie;
import com.erik.movie_find.repositorys.CommentRepository;
import com.erik.movie_find.repositorys.MovieRepository;

@Service
public class CommentService {

    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    
    public CommentService(MovieService movieService, MovieRepository movieRepository, CommentRepository commentRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Long movieId, CommentDTO commentDTO) {

        Comment comment = new Comment(
            commentDTO.getText(),
            commentDTO.getAuthor(),
            movieId
            );

        return commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsByMovie(Long movieId) {

        List<Comment> comments = commentRepository.findByMovieId(movieId);

        return comments.stream()
            .map(comment -> new CommentDTO(
                comment.getMovieId(),
                comment.getText(),
                comment.getAuthor()
            )).toList();
    }

}
