package com.example.butler.repository.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void findPostByKeywordTest(){
        var list = tagRepository.findPostByKeyword("고양이");
        System.out.println(list);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findKeywordByPostIdxTest(){
        var list = tagRepository.findKeywordByPostIdx(53L);
        System.out.println(list);
    }
}