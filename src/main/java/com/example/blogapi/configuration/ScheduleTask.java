package com.example.blogapi.configuration;

import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleTask {
    @Autowired
    private UserRepository userRepository;


    @Scheduled(initialDelay = 5000L,fixedDelay= 1000000L)
    public void deleteScheduler(){
        List<User> users= userRepository.findUsersByDeleteStatus(true);
        if(users.size()==0){
            throw new UserNotFoundException("USER NOT FOUND");

        }
        userRepository.deleteAll(users);
    }
}
