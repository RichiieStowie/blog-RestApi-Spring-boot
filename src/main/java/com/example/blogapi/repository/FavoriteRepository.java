package com.example.blogapi.repository;

import com.example.blogapi.models.Favorites;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites,Long> {
    Optional<Favorites> findFavoritesByUser(User user);

}
