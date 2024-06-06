package com.example.butler.repository;

import com.example.butler.entity.entities.PostEntity;
import com.example.butler.entity.entities.PostImgEntity;
import com.example.butler.entity.entities.UserEntity;
import com.example.butler.entity.entities.UserImgEntity;
import com.example.butler.repository.repositories.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class PostImgRepositoryTest {
    @Autowired
    private PostImgRepository postImgRepository;
    @Autowired
     private UserImgRepository userImgRepository;
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
        var user1 = UserEntity.builder().nick("user1").email("email1").build();
        var userImg = UserImgEntity.builder().userEntity(user1).path("u_path1").build();
        userImgRepository.save(userImg);

        var post1 = PostEntity.builder().title("title1").content("content1").userEntity(user1).build();
        var postImg = PostImgEntity.builder().postEntity(post1).path("p_path1").build();
        postImgRepository.save(postImg);
    }
    @Test
    @Rollback(value = false)
    @Transactional
    void add1UserImgAdd10Test(){
        var user2 = UserEntity.builder().nick("user2").email("email2").build();
        for(int i=0;i<10;i++){
            var userImg = UserImgEntity.builder().userEntity(user2).path("u_path"+i).build();
            userImgRepository.save(userImg);
        }

        var post2 = PostEntity.builder().title("title2").content("content2").userEntity(user2).build();
        for(int i=0;i<10;i++){
            var postImg = PostImgEntity.builder().postEntity(post2).path("p_path"+i).build();
            postImgRepository.save(postImg);
        }

        var user3 = UserEntity.builder().nick("user3").email("email3").build();
        for(int i=0;i<10;i++){
            var userImg = UserImgEntity.builder().userEntity(user3).path("u_path"+i).build();
            userImgRepository.save(userImg);
        }

        var post3 = PostEntity.builder().title("title3").content("content3").userEntity(user3).build();
        for(int i=0;i<10;i++){
            var postImg = PostImgEntity.builder().postEntity(post3).path("p_path"+i).build();
            postImgRepository.save(postImg);
        }
    }

}