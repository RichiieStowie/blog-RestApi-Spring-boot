package com.example.blogapi.service;

import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;

import java.util.Set;

public interface FavoriteService {
    Favorites addPostToFavorites(Long userId, Long postId);
    Set<Post> viewAllPostInFavorites(Long favoriteId);
    void deleteAllPostsInFavorites(Long userId);
    Favorites deleteAPostFromFavorites(Long userId, Long PostId);
}
