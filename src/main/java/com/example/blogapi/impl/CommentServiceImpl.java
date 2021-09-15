package com.example.blogapi.impl;

import com.example.blogapi.exception.CommentNotFoundException;
import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.Comment;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Boolean addNewComment(Long postId,String commentBody,Long userId) {
        Boolean status=false;
       User user = userRepository.getById(userId);
       Post post = postRepository.getById(postId);
        LocalDate localDate= LocalDate.now();
        LocalDateTime localDateTime= LocalDateTime.now();
        LocalTime localTime= LocalTime.now();
        Comment comment = new Comment();
        comment.setCommentBody(commentBody);
        comment.setDateCreated(localDate);
        comment.setUser(user);
        comment.setTimeCreated(localTime);
        comment.setLocalDateTime(localDateTime);
        comment.setPost(post);
        Comment comment1=commentRepository.save(comment);
        if(comment1!=null){
            status=true;
        }else{
            status=false;
        }
        return status;
    }

    @Override
    public List<Comment> displayAllCommentsOnAPost(Long postId) {
        Optional<Post>post= postRepository.findById(postId);
        if(post.isPresent()){
            return commentRepository.findCommentsByPost(post);
        }
        else{
            throw new PostNotFoundException("Post not found");
        }

    }

    @Override
    public void deleteComment(Long commentId,Long userId) {
       Comment comment = commentRepository.getById(commentId);
       User user= comment.getUser();
       if(comment==null){
           throw new CommentNotFoundException("comment does not exist");
       }
       else if(user.getId()!=userId){
           throw new UserNotFoundException("Unauthorized User");
       }
      commentRepository.delete(comment);
    }


}
