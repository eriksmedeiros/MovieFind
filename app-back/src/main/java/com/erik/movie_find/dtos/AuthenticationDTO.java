package com.erik.movie_find.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    private String email;
    private String password;
}
