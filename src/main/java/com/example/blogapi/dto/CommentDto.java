package com.example.blogapi.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String commentBody;
    private Long userId;
    private Long commentId;
}
