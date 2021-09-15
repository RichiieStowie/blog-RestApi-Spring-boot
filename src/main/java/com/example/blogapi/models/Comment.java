package com.example.blogapi.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Comment extends BaseModel{
    @NotBlank
    private String commentBody;
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
