package com.example.butler.repository;

import com.example.butler.repository.repositories.PostImgRepository;
import com.example.butler.repository.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostImgRepository postImgRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void findPostByNickTest(){
        var list = postRepository.findPostByNick("user1");
        System.out.println(list);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findPostImgPathListByIdxTest(){
        var list = postRepository.findPostImgPathListByIdx(5L);
        System.out.println(list);
    }
}