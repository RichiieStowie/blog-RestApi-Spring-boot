package com.example.blogapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @Email
    private String email;
    @Size(min = 4,max = 20)
    private String password;
}
