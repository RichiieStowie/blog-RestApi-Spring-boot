package com.example.blogapi.impl;

import com.example.blogapi.dto.RegistrationDto;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);


    }

    @Test
    void shouldSaveUserSuccessfully(){
        var registrationDto= new RegistrationDto();
        registrationDto.setFirstName("Toun");
        registrationDto.setLastName("Ben");
        registrationDto.setEmail("toun@gmail.com");
        registrationDto.setPassword("1234");
        registrationDto.setUsername("toun");
        var newUser= new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setFirstName(registrationDto.getFirstName().toLowerCase(Locale.ROOT));
        newUser.setLastName(registrationDto.getLastName().toLowerCase(Locale.ROOT));
        newUser.setPassword(registrationDto.getPassword());
        newUser.setUsername(registrationDto.getUsername().toLowerCase(Locale.ROOT));
        BDDMockito.given(userRepository.findUserByEmail(registrationDto.getEmail())).willReturn(Optional.empty());
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
        Mockito.when(userRepository.save(newUser)).then(invocation->invocation.getArgument(0));
        User savedUser= userService.addNewUser(registrationDto);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(registrationDto.getEmail());
    }

    @Test
    void displayAllUsers(){
        List<User> users= new ArrayList<>();
        var user1= new User();
        var user2= new User();
        user1.setUsername("Innocent");
        user1.setId(1L);
        user2.setUsername("tess");
        user2.setId(2L);
        users.add(user1);
        users.add(user2);

        BDDMockito.given(userRepository.findAll()).willReturn(users);
        List<User>expectedUsers= userService.displayAllUsers();
        assertEquals(expectedUsers,users);
    }




}