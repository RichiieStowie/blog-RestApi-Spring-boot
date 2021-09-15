package com.example.blogapi.service;

import com.example.blogapi.models.Comment;

import java.util.List;

public interface CommentService {
    Boolean addNewComment(Long postId,String commentBody,Long userId);
    List<Comment> displayAllCommentsOnAPost(Long postId);
    void deleteComment(Long commentId,Long userId);
}
