package com.example.blogapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
@Data
public class CommentLikes extends BaseModel{
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private User user;
}
