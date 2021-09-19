package com.example.blogapi.controller;

import com.example.blogapi.dto.*;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.impl.FavoriteServiceImpl;
import com.example.blogapi.models.Connection;
import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ConnectionService connectionService;



    @GetMapping("/users")
    public ResponseEntity<List<User>> displayAllUsers(){
        List<User>users= userService.displayAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto){
        userService.updateUserProfile(userDto);
        return new ResponseEntity<>("Profile updated",HttpStatus.OK);
    }


    @PostMapping("/favorites")
    public ResponseEntity<Object> addPostToFavorites(@RequestParam("userId")long userId, @RequestBody PostDto postDto){
        Favorites favorites=favoriteService.addPostToFavorites(userId,postDto.getPostId());
        HttpHeaders headers= new HttpHeaders();
        if(favorites!=null){
            headers.add("message","success");
            return new ResponseEntity<>("Post successfully added",headers,HttpStatus.CREATED);
        }else{
           headers.add("message","failure");
           return new ResponseEntity<>("Post not added",headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @DeleteMapping("/favorites/posts")
    public ResponseEntity<String> deleteAPostFromFavorites(@RequestParam("userId")long userId,@RequestParam("postId")long postId){
        Favorites favorites= favoriteService.deleteAPostFromFavorites(userId, postId);
        HttpHeaders headers= new HttpHeaders();
        if(favorites!=null){
            headers.add("message","Success");
            return new ResponseEntity<>(headers,HttpStatus.ACCEPTED);
        }
        else{
            headers.add("message","Failure");
            return new ResponseEntity<>(headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<String> deleteAllPostFromFavorites(@RequestBody UserDto userDto){
        favoriteService.deleteAllPostsInFavorites(userDto.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUserAccount(@RequestBody UserDto userDto) throws InterruptedException {
        userService.deleteUser(userDto.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cancel-deletion")
    public ResponseEntity<String> cancelDeletion(@RequestBody UserDto userDto){
        userService.cancelDeletion(userDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
