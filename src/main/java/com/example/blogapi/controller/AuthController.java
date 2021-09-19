package com.example.blogapi.controller;

import com.example.blogapi.dto.LoginDto;
import com.example.blogapi.dto.RegistrationDto;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.User;
import com.example.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/signUp")
    public ResponseEntity<String> addNewUser(@Valid  @RequestBody RegistrationDto registrationDto){
        userService.addNewUser(registrationDto);
        return new ResponseEntity<>("User successfully added", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginPage(@Valid @RequestBody LoginDto loginDto){
        User user = userService.loginUser(loginDto.getEmail(), loginDto.getPassword());
        if(user!=null){
            return ResponseEntity.ok().body(user);
        }
        else{
            throw new UserNotFoundException("User does not exist");
        }
    }
}
