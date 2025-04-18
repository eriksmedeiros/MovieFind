package com.erik.movie_find.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    
    private String email;
    private String password;
    private String imageUrl;
    private UserRole role;
}
