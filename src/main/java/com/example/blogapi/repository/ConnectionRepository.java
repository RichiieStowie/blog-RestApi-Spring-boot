package com.example.blogapi.repository;

import com.example.blogapi.models.Connection;
import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Long> {

    Optional<Connection> findConnectionByUserAndConnection(User user,User connection);
    List<Connection> findConnectionsByUser(User user);

}
