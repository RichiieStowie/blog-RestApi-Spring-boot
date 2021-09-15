package com.example.blogapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class PostLikes extends BaseModel{
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
