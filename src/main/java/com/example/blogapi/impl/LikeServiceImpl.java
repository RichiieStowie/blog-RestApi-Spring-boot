package com.example.blogapi.impl;

import com.example.blogapi.exception.CommentNotFoundException;
import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.*;
import com.example.blogapi.repository.*;
import com.example.blogapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private PostLikesRepository postLikesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentLikesRepository commentLikesRepository;


    @Override
    public Boolean likeOrUnlikeAPost(Long postId, Long userId) {
        User user= userRepository.getById(userId);
        Post post = postRepository.getById(postId);
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        LocalDateTime localDateTime= LocalDateTime.now();
        Boolean status;
        if(user==null){
            throw new UserNotFoundException("User does not exist");
        }
        else if(post==null){
            throw new PostNotFoundException("No such Post found");
        }
        else{
            Optional<PostLikes> like= postLikesRepository.findPostLikesByUserAndPost(user,post);
            if(like.isPresent()){
                postLikesRepository.deleteById(like.get().getId());
                status= false;
            }else{
                PostLikes like1 = new PostLikes();
                like1.setPost(post);
                like1.setUser(user);
                like1.setDateCreated(localDate);
                like1.setTimeCreated(localTime);
                like1.setLocalDateTime(localDateTime);
                postLikesRepository.save(like1);
                status= true;
            }
        }
        return status;
    }

    @Override
    public Boolean likeOrUnlikeAComment(Long commentId, Long userId) {
        User user= userRepository.getById(userId);
        Comment comment = commentRepository.getById(commentId);
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        LocalDateTime localDateTime= LocalDateTime.now();
        Boolean status=false;
        if(user==null){
            throw new UserNotFoundException("User does not exist");
        }
        else if(comment==null){
            throw new CommentNotFoundException("Comment does not exist");
        }
        else{
            Optional<CommentLikes> like= commentLikesRepository.findCommentLikesByCommentAndUser(comment,user);
            if(like.isPresent()){
                postLikesRepository.deleteById(like.get().getId());
                status= false;
            }else{
                CommentLikes like1 = new CommentLikes();
                like1.setComment(comment);
                like1.setUser(user);
                like1.setDateCreated(localDate);
                like1.setTimeCreated(localTime);
                like1.setLocalDateTime(localDateTime);
                commentLikesRepository.save(like1);
                status= true;
            }
        }
        return status;
    }
}
