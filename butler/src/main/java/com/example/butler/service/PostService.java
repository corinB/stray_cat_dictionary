package com.example.butler.service;

import com.example.butler.dto.PostDto;
import com.example.butler.dto.PostLikeDto;
import com.example.butler.entity.entities.PostEntity;
import com.example.butler.entity.entities.PostImgEntity;
import com.example.butler.entity.entities.PostLikeEntity;
import com.example.butler.repository.repositories.PostImgRepository;
import com.example.butler.repository.repositories.PostLikeRepository;
import com.example.butler.repository.repositories.PostRepository;
import com.example.butler.repository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostImgRepository postImgRepository;

    @Transactional
    public PostDto writePost(PostDto dto) {
        try {
            var post = postRepository.save(makeNewPostEntity(dto));
            dto.getPostImgList().forEach(path ->{
                postImgRepository.save(toPostImgEntity(post, path));
            });
            return ThisPosTotDto(post);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    // 포스트 이미지 생성
    private PostImgEntity toPostImgEntity(PostEntity postEntity, String path){
        return PostImgEntity.builder().postEntity(postEntity).path(path).build();
    }

    private PostEntity makeNewPostEntity(PostDto dto){
        var user = userRepository.findById(dto.getUserIdx()).get();
        return PostEntity.builder().userEntity(user).title(dto.getTitle()).content(dto.getContent()).build();
    }

    private PostDto ThisPosTotDto(PostEntity entity){

        return PostDto.builder()
                .userIdx(entity.getUserEntity().getIdx())
                .title(entity.getTitle())
                .content(entity.getContent()).postImgList(postRepository
                        .findPostImgPathListByIdx(entity.getIdx()))
                .likeCount(postRepository.findPostLikeListByIdx(entity.getIdx()).size())
                .build();
    }

    public String addLike(PostLikeDto dto) {
        try {
            postLikeRepository.save(toLikeEntity(dto));
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    private PostLikeEntity toLikeEntity(PostLikeDto dto){
        var user = userRepository.findById(dto.getUserIdx()).get();
        var post = postRepository.findById(dto.getPostIdx()).get();
        return PostLikeEntity.builder().userEntity(user).postEntity(post).build();
    }

}
