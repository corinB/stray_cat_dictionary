package com.example.butler.repository;

import com.example.butler.entity.entities.PostEntity;
import com.example.butler.repository.repositories.PostImgRepository;
import com.example.butler.repository.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.annotation.Rollback;

import java.util.List;

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

    @Test
    @Transactional
    @Rollback(value = false)
    void searchByTitle(){
        var result = postRepository.searchByTitle("다간");
        System.out.println(result);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void landingPage(){
        var result = postRepository.landingPage();
        System.out.println(result);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findAll(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("updateAt").descending());
        Page<PostEntity> result = postRepository.findAll(pageable);
        var pageNum = result.getTotalPages();
        var totalElements = result.getTotalElements();
        System.out.println("totalPages : "+pageNum);
        System.out.println("totalElements : "+totalElements);
        List<PostEntity> list = result.getContent();
        System.out.println(list.size());
    }
}