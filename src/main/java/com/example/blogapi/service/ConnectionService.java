package com.example.blogapi.service;

import com.example.blogapi.dto.UserDto;
import com.example.blogapi.models.Connection;
import com.example.blogapi.models.Post;

import java.util.List;
import java.util.Set;

public interface ConnectionService {
    Connection addUserToConnection(UserDto user, Long userId) throws Exception;
    List<Post> displayAllConnectionsPost(Long userId);
}
