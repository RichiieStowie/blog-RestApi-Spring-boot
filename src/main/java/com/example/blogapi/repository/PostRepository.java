package com.example.blogapi.repository;

import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByUser(User user);
    List<Post> findAllByPostBodyIsContaining(String keyword);
}
