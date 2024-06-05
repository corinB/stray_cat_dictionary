package com.example.butler.repository;

import com.example.butler.entity.CommentEntity;
import com.example.butler.entity.PostEntity;
import com.example.butler.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostImgRepositoryTest {
    @Autowired
    private PostImgRepository postImgRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    void test1(){
//        var user1 = UserEntity.builder().nick("user1").email("email1").build();
    }

}