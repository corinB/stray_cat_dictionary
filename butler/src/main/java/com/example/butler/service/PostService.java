package com.example.butler.service;

import com.example.butler.dto.CommentDto;
import com.example.butler.dto.CommentLiKeDto;
import com.example.butler.dto.PostDto;
import com.example.butler.dto.PostLikeDto;
import com.example.butler.entity.entities.*;
import com.example.butler.repository.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostImgRepository postImgRepository;
    private final TagRepository tagRepository;

    @Transactional
    public PostDto writePost(PostDto dto) {  // 게시글 작성 완성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            var post = makeNewPostEntity(userEntity, dto);
            dto.getPostImgList().forEach(path -> {
                var img = postDtoToImgEntity(post, path);
                post.addImg(img);
            });
            dto.getTagList().forEach(keyword -> {
                var tag = makeNewTagEntity(post, keyword);
                post.addTag(tag);
            });
            postRepository.save(post);
        });
        return dto;
    }

    // postImgEntity 생성
    private PostImgEntity postDtoToImgEntity(PostEntity postEntity, String path){
        return PostImgEntity.builder().postEntity(postEntity).path(path).build();
    }

    // PostEntity 생성
    private PostEntity makeNewPostEntity(UserEntity user, PostDto dto){
        return PostEntity.builder().userEntity(user).title(dto.getTitle()).content(dto.getContent()).build();
    }

    //tagEntity 생성
    private TagEntity makeNewTagEntity(PostEntity post,String keyword){
        return TagEntity.builder().postEntity(post).keyword(keyword).build();
    }

    @Transactional
    public CommentDto writeComment(CommentDto dto) { // 댓글 완성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            postRepository.findById(dto.getPostIdx()).ifPresent(postEntity ->{
                var comment = makeNewCommentEntity(userEntity, postEntity, dto);
                commentRepository.save(comment);
            });
        });
        return dto;
    }

    private CommentEntity makeNewCommentEntity(UserEntity user, PostEntity post, CommentDto dto){
        return CommentEntity.builder().userEntity(user).postEntity(post).content(dto.getContent()).build();
    }

    @Transactional
    public PostDto addPostLike(PostLikeDto dto) { // 게시글 좋아요 작성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            postRepository.findById(dto.getPostIdx()).ifPresent(postEntity ->{
                postLikeRepository.save(makeNewPostLikeEntity(userEntity, postEntity));
            });
        });
        return changePostDto(postRepository.findById(dto.getPostIdx()).get());
    }

    private PostDto changePostDto(PostEntity postEntity){
        return PostDto.builder().likeCount(postEntity.getPostLikeEntityList().size())
                .content(postEntity.getContent()).title(postEntity.getTitle())
                .userIdx(postEntity.getUserEntity().getIdx()).build();
    }

    private PostLikeEntity makeNewPostLikeEntity(UserEntity user, PostEntity post){
        return PostLikeEntity.builder().userEntity(user).postEntity(post).build();
    }

    @Transactional
    public CommentDto addCommentLike(CommentLiKeDto commentLiKeDto) { // 댓글 좋아요 작성 완료
        userRepository.findById(commentLiKeDto.getUserIdx()).ifPresent(userEntity ->{
            commentRepository.findById(commentLiKeDto.getCommentIdx()).ifPresent(commentEntity ->{
                commentLikeRepository.save(makeNewCommentLikeEntity(userEntity, commentEntity));
            });
        });
        return changeCommentDto(commentRepository.findById(commentLiKeDto.getCommentIdx()).get());
    }

    private CommentLikeEntity makeNewCommentLikeEntity(UserEntity user, CommentEntity comment){
        return CommentLikeEntity.builder().userEntity(user).commentEntity(comment).build();
    }

    private CommentDto changeCommentDto(CommentEntity commentEntity){
        return CommentDto.builder().likeCount(commentEntity.getCommentLikeEntityList().size())
                .content(commentEntity.getContent()).build();
    }

    @Transactional
    public void upDateTag(long postIdx, String tag){
        postRepository.findById(postIdx).ifPresent(postEntity -> {
            tagRepository.save(makeNewTagEntity(postEntity, tag));
        });
    }


    @Transactional
    public void deleteTag(Long tagIdx) {
        tagRepository.findById(tagIdx).ifPresent(tagEntity -> {
            log.info("tagEntity : " + tagEntity);
        });
        tagRepository.deleteById(tagIdx);
    }

    @Transactional
    public List<String> getAllPostImgPath(long postIdx){
        return postRepository.findPostImgPathListByIdx(postIdx);
    }

    @Transactional
    public void deleteAllPostImg(long postIdx){
        postRepository.deletePostImgAllQuery(postIdx);
    }

    @Transactional
    public void deletePost(Long postIdx) {
        postRepository.deleteById(postIdx);
    }

    @Transactional
    public void deleteComment(Long commentIdx) {
        commentRepository.deleteById(commentIdx);
    }

    @Transactional
    public void deletePostLike(Long postLikeIdx) {
        postLikeRepository.deleteById(postLikeIdx);
    }

    @Transactional
    public void deleteCommentLike(Long commentLikeIdx) {
        commentLikeRepository.deleteById(commentLikeIdx);
    }

    @Transactional
    public void updatePostImg(long postIdx, String path) {
        postRepository.findById(postIdx).ifPresent(postEntity -> {
            postImgRepository.save(PostImgEntity
                    .builder().path(path)
                    .postEntity(postEntity)
                    .build());
        });
    }

    @Transactional
    public void updatePost(PostDto dto , Long postIdx) {
        postRepository.findById(postIdx).ifPresent(postEntity -> {
            postEntity.setTitle(dto.getTitle());
            postEntity.setContent(dto.getContent());
            postRepository.save(postEntity);
        });
    }



}
