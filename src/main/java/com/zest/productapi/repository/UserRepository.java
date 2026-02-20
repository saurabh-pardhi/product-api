package com.zest.productapi.repository;

import com.zest.productapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Fetch user by username for authentication
    Optional<User> findByUsername(String username);
}