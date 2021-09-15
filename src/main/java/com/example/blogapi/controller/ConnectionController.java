package com.example.blogapi.controller;

import com.example.blogapi.dto.UserDto;
import com.example.blogapi.models.Connection;
import com.example.blogapi.models.Post;
import com.example.blogapi.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user/connections")
public class ConnectionController {
    @Autowired
    private ConnectionService connectionService;

    @PostMapping("/new-connection")
    public ResponseEntity<String> addUserToConnectionList(@RequestParam(name="userId")Long userId, @RequestBody UserDto user) throws Exception {
        Connection connection=connectionService.addUserToConnection(user,userId);
        if(connection!=null){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> displayAllConnectionsPost(@RequestParam("userId")Long userId){
        List<Post> posts=connectionService.displayAllConnectionsPost(userId);
        return new ResponseEntity<>(posts,HttpStatus.ACCEPTED);

    }
}
