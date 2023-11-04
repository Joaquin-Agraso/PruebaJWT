package com.example.demo.loginjwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.loginjwt.User.User;



public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}