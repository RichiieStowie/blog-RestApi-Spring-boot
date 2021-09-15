package com.example.blogapi.service;

public interface LikeService {
    Boolean likeOrUnlikeAPost(Long postId, Long userId);
    Boolean likeOrUnlikeAComment(Long commentId,Long userId);
}
