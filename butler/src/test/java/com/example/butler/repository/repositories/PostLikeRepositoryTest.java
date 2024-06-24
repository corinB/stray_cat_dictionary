package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PostLikeEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostLikeRepositoryTest {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private  UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    void iLikeThisPost(){

        var user = userRepository.getEntityByIdx(1L);

        var post = postRepository.getEntityByIdx(1L);

        var like = PostLikeEntity.builder().userEntity(user).postEntity(post).build();

        postLikeRepository.save(like);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    void countPostLikeByPostIdx(){
        var count = postLikeRepository.countPostLikeByPostIdx(7L);
        System.out.println(count);
    }

}