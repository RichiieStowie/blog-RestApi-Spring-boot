package com.example.blogapi.repository;

import com.example.blogapi.models.Comment;
import com.example.blogapi.models.CommentLikes;
import com.example.blogapi.models.PostLikes;
import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
    Optional<CommentLikes> findCommentLikesByCommentAndUser(Comment comment, User user);
}
