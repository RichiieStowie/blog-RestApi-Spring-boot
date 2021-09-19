package com.example.blogapi.service;

import com.example.blogapi.dto.RegistrationDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User addNewUser(RegistrationDto registrationDto);
    void updateUserProfile(UserDto userDto);
    List<User> displayAllUsers();
    User loginUser(String email,String password);
    void deleteUser(long userId) throws InterruptedException;
    void cancelDeletion(long userId);
}
