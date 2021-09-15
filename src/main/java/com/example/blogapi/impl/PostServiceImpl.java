package com.example.blogapi.impl;

import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.FavoriteRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;


    @Override
    public void createNewPost(String postBody, Long id) {
        Post post = new Post();
        LocalDate localDate=LocalDate.now();
        LocalDateTime localDateTime= LocalDateTime.now();
        LocalTime localTime= LocalTime.now();
        User user = userRepository.getById(id);
        if(user==null){
            throw new UserNotFoundException("User does not exist");
        }
        post.setPostBody(postBody);
        post.setDateCreated(localDate);
        post.setLocalDateTime(localDateTime);
        post.setTimeCreated(localTime);
        post.setUser(user);
        postRepository.save(post);

    }

    @Override
    public List<Post> displayAllPost() {
      return postRepository.findAll();
    }

    @Override
    public List<Post> displaySearchedPosts(String keyword) {
        return postRepository.findAllByPostBodyIsContaining(keyword);
    }
}
