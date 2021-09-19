package com.example.blogapi.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class RegistrationDto {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 4,max = 20)
    private String password;
    @NotNull
    @NotBlank
    private String username;
}
