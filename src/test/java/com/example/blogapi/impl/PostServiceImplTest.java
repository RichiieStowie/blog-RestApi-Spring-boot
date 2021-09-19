package com.example.blogapi.impl;

import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setup(){


    }

    @Test
    void newPostAddedSuccessfully(){
        Long userId= 1L;
        String postBody="what is happening";
        User user1 = new User();
        user1.setUsername("seun");
        user1.setEmail("seun@gmail.com");
        user1.setId(userId);
        Post post = new Post();
        post.setUser(user1);
        post.setPostBody(postBody);
        BDDMockito.given(userRepository.getById(userId)).willReturn(user1);
        BDDMockito.given(postRepository.save(post)).willReturn(post);

        Post post1= postService.createNewPost(postBody,userId);
        assertThat(post1.getPostBody()).isEqualTo(postBody);
        assertThat(post1.getUser().getId()).isEqualTo(userId);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void shouldThrowErrorWhenPostISMadeWithUnRegisteredUser(){
        Long userId= 2L;
        String postBody="what is happening";
        BDDMockito.given(userRepository.getById(userId)).willReturn(null);

        assertThrows(UserNotFoundException.class,()->{postService.createNewPost(postBody,userId);});
    }
}
