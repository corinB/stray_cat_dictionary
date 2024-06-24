package com.example.butler.service;

import com.example.butler.dto.CommentDto;
import com.example.butler.dto.PostDto;
import com.example.butler.entity.entities.PostLikeEntity;
import com.example.butler.repository.repositories.PostLikeRepository;
import com.example.butler.repository.repositories.PostRepository;
import com.example.butler.repository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    void writeComment(){
        var dto = CommentDto.builder()
                .userIdx(3)
                .postIdx(7)
                .content("test")
                .build();
        var result = postService.writeComment(dto);
        System.out.println(result);
    }

    @Test
    @Rollback(value = false)

    void updatePost(){
        System.out.println("사진이 삭제됩니다");
        var oldImgList = postRepository.findPostImgPathListByIdx(14L);
        System.out.println(oldImgList);

        postService.deleteAllPostImg(14L);

        oldImgList.add(1,"새 이미지");
        oldImgList.remove(3);
        oldImgList.remove(0);

        oldImgList.forEach(path -> {
            postService.updatePostImg(14L, path);
        });

        var dto = PostDto.builder().userIdx(1L).title("test update")
                .content("이번엔 잘 되냐?")
                .build();

        postService.updatePost(dto, 14L);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    void updateTag(){
        postService.deleteTag(2L);
        postService.upDateTag(14L, "고양이");
        postService.upDateTag(14L, "츄르");

    }

//    @Test
//    @Rollback(value = false)
//    @Transactional
//    void landing(){
//        var page = postService.landingPage();
//
//        System.out.println(page.size());
//        System.out.println(page);
//    }


    @Test
    @Rollback(value = false)
    @Transactional
    void getPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("updateAt").descending());
        var page = postService.getPage(pageable);
        System.out.println(page.size());
        System.out.println(page);
    }
}