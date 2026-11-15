package com.example.webapp_network_system.repository;

import com.example.webapp_network_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername (String username);

}
