package com.example.butler.service;

import com.example.butler.dto.PostLikeDto;
import com.example.butler.entity.entities.PostLikeEntity;
import com.example.butler.repository.repositories.PostLikeRepository;
import com.example.butler.repository.repositories.PostRepository;
import com.example.butler.repository.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public String addLike(PostLikeDto dto) {
        try {
            postLikeRepository.save(toPostLikeEntity(dto));
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    private PostLikeEntity toPostLikeEntity(PostLikeDto dto){
        var user = userRepository.findById(dto.getUserIdx()).get();
        var post = postRepository.findById(dto.getPostIdx()).get();
        return PostLikeEntity.builder().userEntity(user).postEntity(post).build();
    }
}
