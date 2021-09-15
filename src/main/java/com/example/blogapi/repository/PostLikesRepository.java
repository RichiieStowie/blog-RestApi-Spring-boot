package com.example.blogapi.repository;

import com.example.blogapi.models.Comment;
import com.example.blogapi.models.PostLikes;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes,Long> {

    Optional<PostLikes> findPostLikesByUserAndPost(User user, Post post);


}
