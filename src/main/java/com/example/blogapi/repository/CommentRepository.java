package com.example.blogapi.repository;

import com.example.blogapi.models.Comment;
import com.example.blogapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPost(Optional<Post> post);
    List<Comment> findCommentsByCommentBodyContaining(String keyword);
}
