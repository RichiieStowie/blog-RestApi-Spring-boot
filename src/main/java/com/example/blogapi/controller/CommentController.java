package com.example.blogapi.controller;

import com.example.blogapi.dto.CommentDto;
import com.example.blogapi.dto.PostDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.models.Comment;
import com.example.blogapi.service.CommentService;
import com.example.blogapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;


    @PostMapping("posts/newComment")
    public ResponseEntity<String> addNewComment(@RequestParam("userId")long userId, @RequestParam("postId")long postId, @RequestBody String commentBody){
        Boolean status=commentService.addNewComment(postId, commentBody, userId);
        if(status){
            return new ResponseEntity<>("New comment created", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("posts/comments")
    public ResponseEntity<List<Comment>> displayAllComments(@RequestParam(name = "postId")Long postId){
        List<Comment> commentList= commentService.displayAllCommentsOnAPost(postId);
        return ResponseEntity.ok().body(commentList);
    }

    @DeleteMapping("posts/comments")
    public  ResponseEntity<String> deleteComment(@RequestParam("userId")long userId, @RequestParam("commentsId")long commentId){
        commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>("DELETED SUCCESSFULLY",HttpStatus.NO_CONTENT);
    }

    @PostMapping("/posts/comments/like-Comment")
    public  ResponseEntity<String> likeUnlikeAComment(@RequestParam("userId")long userId, @RequestParam("commentId")long commentId){
        Boolean status= likeService.likeOrUnlikeAComment(userId, commentId);
        HttpHeaders headers = new HttpHeaders();
        if(status){
            headers.add("message","Success");
            return new ResponseEntity<>("Comment successfully liked",headers, HttpStatus.CREATED);
        }else{
            headers.add("message","success");
            return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);
        }
    }

}
