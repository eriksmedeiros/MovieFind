package com.erik.movie_find.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long movieId;
    private String text;
    private String author;
}
