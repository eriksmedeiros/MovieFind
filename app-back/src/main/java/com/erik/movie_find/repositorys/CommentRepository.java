package com.erik.movie_find.repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.movie_find.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByMovieId(Long movieId);
}
