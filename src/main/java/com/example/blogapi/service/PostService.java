package com.example.blogapi.service;

import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(String postBody,Long id);
    List<Post> displayAllPost();
    List<Post> displaySearchedPosts(String keyword);


}
