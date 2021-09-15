package com.example.blogapi.dto;

import lombok.Data;

@Data
public class PostDto {
    private String contentBody;
    private Long userId;
    private Long postId;

}
