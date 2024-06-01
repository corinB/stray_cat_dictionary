package com.example.butler.repository;

import com.example.butler.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Rollback(false)
    @Test
    void addUser(){
        var user1 = UserEntity.builder().nick("user1").email("email1").build();
        userRepository.save(user1);

    }

}