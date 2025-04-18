package com.erik.movie_find.dtos;

// ENUM para as Roles de permissões dos usuários
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
