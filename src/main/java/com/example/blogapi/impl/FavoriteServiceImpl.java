package com.example.blogapi.impl;

import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.FavoriteRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;



    @Override
    public Favorites addPostToFavorites(Long userId, Long postId) {
        LocalDate localDate= LocalDate.now();
        LocalTime localTime =LocalTime.now();
        LocalDateTime localDateTime=LocalDateTime.now();
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);
        System.out.println(user.getFirstName());
        log.debug(user.getUsername());
        if(user==null){
            throw new UserNotFoundException("User does not exist");
        }
        else  if(post==null){
            throw new PostNotFoundException("Post not found");
        }
        else{
            Optional<Favorites> favorites= favoriteRepository.findFavoritesByUser(user);
           if(!favorites.isPresent()){
               Favorites favorites1= new Favorites();
               favorites1.setUser(user);
               favorites1.setTimeCreated(localTime);
               favorites1.setDateCreated(localDate);
               favorites1.setLocalDateTime(localDateTime);
               Set<Post>postList= new HashSet<>();
               postList.add(post);
               favorites1.setPosts(postList);
               return favoriteRepository.save(favorites1);
           }else{
               Set<Post>posts=favorites.get().getPosts();
               posts.add(post);
               favorites.get().setPosts(posts);
               return favoriteRepository.save(favorites.get());
           }
        }


    }

    @Override
    public Set<Post> viewAllPostInFavorites(Long favoriteId) {
        Favorites favorites= favoriteRepository.getById(favoriteId);
        Set<Post>postList = favorites.getPosts();
        return postList;
    }

    @Override
    public void deleteAllPostsInFavorites(Long userId) {
        User user = userRepository.getById(userId);
        Favorites favorites= favoriteRepository.findFavoritesByUser(user).get();
        favoriteRepository.delete(favorites);
    }

    @Override
    public Favorites deleteAPostFromFavorites(Long userId, Long postId) {
        User user = userRepository.getById(userId);
        Favorites favorites= favoriteRepository.findFavoritesByUser(user).get();
        Post post= postRepository.getById(postId);
        System.out.println(post.getPostBody());
        if(post==null){
            throw new PostNotFoundException("Post not found");
        }
        Set<Post>postList= favorites.getPosts();
        for (Post postItem:postList){
            if(postItem.getId()==post.getId()){
                postList.remove(postItem);
                favorites.setPosts(postList);
            }
            else{
                throw new PostNotFoundException("Post not present in favorites");
            }
        }
        return favoriteRepository.save(favorites);
    }
}
