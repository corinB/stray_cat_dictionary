package com.example.butler.repository;

import com.example.butler.repository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    void findMyProfileImgTest(){
        var pathList = userRepository.findMyProfileImg(1L);
        System.out.println(pathList);
    }

}