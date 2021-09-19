package com.example.blogapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User extends BaseModel{
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String username;
    private Boolean deleteStatus;
    @JsonIgnore
    @OneToOne
    private Favorites favorites;
}
