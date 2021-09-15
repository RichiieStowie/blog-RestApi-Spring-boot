package com.example.blogapi.impl;

import com.example.blogapi.dto.UserDto;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.models.Connection;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.ConnectionRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Connection addUserToConnection(UserDto user, Long userId) {
        LocalDate localDate= LocalDate.now();
        LocalTime localTime =LocalTime.now();
        LocalDateTime localDateTime=LocalDateTime.now();
        User mainUser= userRepository.getById(userId);
        User connectedUser= userRepository.getById(user.getId());
        Optional<Connection> connection= connectionRepository.findConnectionByUserAndConnection(mainUser,connectedUser);
        if(!connection.isPresent()){
            Connection connection1= new Connection();
            connection1.setConnection(connectedUser);
            connection1.setUser(mainUser);
            connection1.setTimeCreated(localTime);
            connection1.setLocalDateTime(localDateTime);
            connection1.setDateCreated(localDate);
           return connectionRepository.save(connection1);
        }

        else{
            throw new ResourceNotFoundException("Connection to this user already exists");
        }
    }

    @Override
    public List<Post> displayAllConnectionsPost(Long userId) {
        User user= userRepository.getById(userId);
        List<Post>posts = new ArrayList<>();
        List<Connection> connectionList= connectionRepository.findConnectionsByUser(user);
        for(Connection connection:connectionList){
            User user1= connection.getConnection();
            List<Post> postList= postRepository.findPostsByUser(user1);
            posts.addAll(postList);
        }
        return posts;
    }


}
