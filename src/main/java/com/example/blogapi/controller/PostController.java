package com.example.blogapi.controller;

import com.example.blogapi.dto.PostDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.models.Post;
import com.example.blogapi.service.LikeService;
import com.example.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;

    @PostMapping("/newPost")
    public ResponseEntity<String> createNewPost(@RequestBody String postBody, @RequestParam("userId")long userId){
        postService.createNewPost(postBody.toLowerCase(), userId);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> displayAllPost(){

      List<Post>postList= postService.displayAllPost();
      return ResponseEntity.ok().body(postList);
    }

   @GetMapping("/search-posts")
    public ResponseEntity<List<Post>>  displaySearchedPosts(@RequestBody String keyword){
       HttpHeaders headers= new HttpHeaders();
       headers.add("message","success");
       List<Post>posts= postService.displaySearchedPosts(keyword.toLowerCase(Locale.ROOT));
       return new ResponseEntity<>(posts,headers,HttpStatus.ACCEPTED);
   }

    @PostMapping("/posts/like-Post")
    public  ResponseEntity<String> likeUnlikeAPost(@RequestParam("userId")long userId,@RequestParam("postId")long postId){
        Boolean status= likeService.likeOrUnlikeAPost(postId, userId);
        HttpHeaders headers = new HttpHeaders();
        if(status){
            headers.add("message","Success");
            return new ResponseEntity<>("Post successfully liked",headers, HttpStatus.CREATED);
        }else{
            headers.add("message","success");
            return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);
        }
    }
}
