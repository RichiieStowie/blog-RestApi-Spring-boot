package com.example.blogapi.impl;

import com.example.blogapi.dto.RegistrationDto;
import com.example.blogapi.dto.UserDto;
import com.example.blogapi.exception.RegistrationException;
import com.example.blogapi.models.User;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUserProfile(UserDto userDto) {
        Optional<User>user = userRepository.findUserByEmail(userDto.getEmail());
        if(user.isPresent()){
            user.get().setUsername(userDto.getUsername());
            user.get().setLastName(userDto.getLastName());
            user.get().setFirstName(userDto.getFirstName());
            userRepository.save(user.get());
        }
    }

    @Override
    public User addNewUser(RegistrationDto registrationDto) {
       User user =new User();
        Optional<User>user1 = userRepository.findUserByEmail(registrationDto.getEmail());
        if(user1.isPresent()){
            throw new RegistrationException("User already exists");
        }
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        LocalDateTime localDateTime= LocalDateTime.now();
       user.setEmail(registrationDto.getEmail().toLowerCase(Locale.ROOT));
       user.setUsername(registrationDto.getUsername().toLowerCase(Locale.ROOT));
       user.setPassword(registrationDto.getPassword().toLowerCase(Locale.ROOT));
       user.setFirstName(registrationDto.getFirstName().toLowerCase(Locale.ROOT));
       user.setLastName(registrationDto.getLastName().toLowerCase(Locale.ROOT));
       user.setDateCreated(localDate);
       user.setTimeCreated(localTime);
       user.setLocalDateTime(localDateTime);
       log.info(user.getUsername()+" is saved");
        return userRepository.save(user);
    }

    @Override
    public List<User> displayAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User loginUser(String email, String password) {
     return userRepository.findUserByEmailAndPassword(email,password);

    }



    @Override
    public void deleteUser(long userId) throws InterruptedException {
        User user = userRepository.getById(userId);
        user.setDeleteStatus(true);
        userRepository.save(user);
     log.info("Deleting user");
     User user1= userRepository.getById(userId);
     if(user1.getDeleteStatus()){
         log.info("account deleted"+ user1.getDeleteStatus());
         userRepository.deleteById(userId);
     }else if(user1.getDeleteStatus()==false){
         log.info("deletion suspended");
     }
    }

    @Override
    public void cancelDeletion(long userId) {
        User user= userRepository.getById(userId);
        if(user.getDeleteStatus()){
            log.info("deletion would be canceled");
            user.setDeleteStatus(false);
            log.info("delete status now"+ user.getDeleteStatus());
            userRepository.save(user);
        }else{
            log.info("everything remains the same");
        }
    }
}
