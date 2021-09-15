package com.example.blogapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Favorites extends BaseModel{
//    private LocalDate dateCreated;
//    private LocalTime timeCreated;
    @OneToOne(cascade= CascadeType.ALL)
    private User user;
    @ManyToMany(targetEntity = Post.class)
   private Set<Post> posts;

}
