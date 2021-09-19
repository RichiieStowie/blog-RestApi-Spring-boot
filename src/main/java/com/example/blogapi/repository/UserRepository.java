package com.example.blogapi.repository;

import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndPassword(String email, String password);
    Optional<User>  findUserByEmail(String email);
    List<User> findUsersByDeleteStatus(Boolean status);
}
