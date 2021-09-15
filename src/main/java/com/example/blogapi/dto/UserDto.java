package com.example.blogapi.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Long id;
}
