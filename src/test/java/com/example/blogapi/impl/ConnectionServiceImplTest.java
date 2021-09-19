package com.example.blogapi.impl;

import com.example.blogapi.dto.UserDto;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.models.Connection;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.ConnectionRepository;
import com.example.blogapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ConnectionServiceImplTest {
    @Mock
    private ConnectionRepository connectionRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ConnectionServiceImpl connectionService;


    @Test
    void newConnectionAddedSuccessfully(){
        var userDto = new UserDto();
        userDto.setId(1L);
        Long userId= 2L;
        var user1= new User();
        user1.setId(userDto.getId());
        var user2= new User();
        Connection connection= new Connection();
        connection.setConnection(user1);
        connection.setUser(user2);
        user2.setId(userId);
        BDDMockito.given(userRepository.getById(userDto.getId())).willReturn(user1);
        BDDMockito.given(userRepository.getById(userId)).willReturn(user2);
        BDDMockito.given(connectionRepository.findConnectionByUserAndConnection(user2,user1)).willReturn(Optional.empty());
        BDDMockito.given(connectionRepository.save(connection)).willReturn(connection);
       Connection connection1= connectionService.addUserToConnection(userDto,userId);
        assertThat(connection1).isNotNull();
    }

    @Test
    void shouldThrowErrorConnectionAlreadyExists(){
        var userDto= new UserDto();
        userDto.setId(1L);
        var user1= new User();
        user1.setId(userDto.getId());

        long userId=2l;
        var user2= new User();
        user2.setId(userId);

        var connection = new Connection();
        connection.setUser(user1);
        connection.setConnection(user2);

        BDDMockito.given(userRepository.getById(userDto.getId())).willReturn(user1);
        BDDMockito.given(userRepository.getById(userId)).willReturn(user2);
        BDDMockito.given(connectionRepository.findConnectionByUserAndConnection(user2,user1)).willReturn(Optional.of(connection));

        assertThrows(ResourceNotFoundException.class,()->connectionService.addUserToConnection(userDto,userId));
    }
}
