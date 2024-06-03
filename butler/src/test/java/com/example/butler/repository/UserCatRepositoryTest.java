package com.example.butler.repository;

import com.example.butler.entity.UserCatEntity;
import com.example.butler.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCatRepositoryTest {

    @Autowired
    private UserCatRepository userCatRepository;

    @Autowired
    private UserRepository userRepository;


    @Rollback(value = false)
    @Transactional
    @Test
    void addCat(){
      var user = UserEntity.builder().nick("kim").email("email@gmail").build();
      //userRepository.save(user); 이거 안해도 자동 저장됨git add.

      var userCat13 = UserCatEntity.builder().name("cat14").des("des14").userEntity(user).build();

      userCatRepository.save(userCat13);
    }

}