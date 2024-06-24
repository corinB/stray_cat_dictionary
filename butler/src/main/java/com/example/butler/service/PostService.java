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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Value("${dir.img.post}")
    private String uploadDir;

    @Transactional
    public PostDto writePost(PostDto dto, List<MultipartFile> postImgFiles) {
        // 업로드된 이미지 파일들을 저장하고 저장된 경로들을 리스트에 담습니다.
        List<String> imgPaths = saveFiles(postImgFiles);
        dto.setPostImgList(imgPaths); // DTO에 저장된 이미지 경로 리스트를 설정합니다.

        // 사용자 ID로 사용자 엔티티를 조회하여 게시글 엔티티를 생성하고 저장합니다.
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity -> {
            var post = makeNewPostEntity(userEntity, dto); // 새 게시글 엔티티 생성
            dto.getPostImgList().forEach(path -> {
                var img = postDtoToImgEntity(post, path); // 이미지 엔티티 생성
                post.addImg(img); // 게시글에 이미지 추가
            });
            dto.getPostTagList().forEach(keyword -> {
                var tag = makeNewTagEntity(post, keyword); // 태그 엔티티 생성
                post.addTag(tag); // 게시글에 태그 추가
            });
            postRepository.save(post); // 게시글 저장
        });

        return dto; // 처리된 DTO 반환
    }

    /**
     * 업로드된 이미지 파일들을 저장하고 저장된 파일 경로들의 리스트를 반환합니다.
     *
     * @param files 업로드된 이미지 파일들
     * @return 저장된 파일 경로들의 리스트
     */
    private List<String> saveFiles(List<MultipartFile> files) {
        List<String> imgPaths = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                try {
                    file.transferTo(dest); // 파일을 저장합니다.
                    imgPaths.add(fileName); // 저장된 파일 이름
                } catch (IOException e) {
                    log.error("파일 저장 실패: {}", fileName, e);
                }
            }
        }
        return imgPaths; // 저장된 파일 경로들의 리스트를 반환합니다.
    }

    /**
     * 게시글 엔티티와 이미지 경로를 사용하여 이미지 엔티티를 생성합니다.
     *
     * @param postEntity 게시글 엔티티
     * @param path 이미지 파일 경로
     * @return 생성된 이미지 엔티티
     */
    private PostImgEntity postDtoToImgEntity(PostEntity postEntity, String path) {
        return PostImgEntity.builder().postEntity(postEntity).path(path).build();
    }

    /**
     * 사용자 엔티티와 게시글 DTO를 사용하여 새 게시글 엔티티를 생성합니다.
     *
     * @param user 사용자 엔티티
     * @param dto 게시글 DTO
     * @return 생성된 게시글 엔티티
     */
    private PostEntity makeNewPostEntity(UserEntity user, PostDto dto) {
        return PostEntity.builder()
                .userEntity(user).title(dto.getTitle())
                .content(dto.getContent())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .build();
    }

    /**
     * 게시글 엔티티와 키워드를 사용하여 새 태그 엔티티를 생성합니다.
     *
     * @param post 게시글 엔티티
     * @param keyword 태그 키워드
     * @return 생성된 태그 엔티티
     */
    private TagEntity makeNewTagEntity(PostEntity post, String keyword) {
        return TagEntity.builder().postEntity(post).keyword(keyword).build();
    }

//    @Transactional
//    public PostDto writePost(PostDto dto) {  // 게시글 작성 완성
//        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
//            var post = makeNewPostEntity(userEntity, dto);
//            dto.getPostImgList().forEach(path -> {
//                var img = postDtoToImgEntity(post, path);
//                post.addImg(img);
//            });
//            dto.getPostTagList().forEach(keyword -> {
//                var tag = makeNewTagEntity(post, keyword);
//                post.addTag(tag);
//            });
//            postRepository.save(post);
//        });
//        return dto;
//    }
//
//    // postImgEntity 생성
//    private PostImgEntity postDtoToImgEntity(PostEntity postEntity, String path){
//        return PostImgEntity.builder().postEntity(postEntity).path(path).build();
//    }
//
//    // PostEntity 생성
//    private PostEntity makeNewPostEntity(UserEntity user, PostDto dto){
//        return PostEntity.builder()
//                .userEntity(user).title(dto.getTitle())
//                .content(dto.getContent())
//                .longitude(dto.getLongitude())
//                .latitude(dto.getLatitude())
//                .build();
//    }
//
//    //tagEntity 생성
//    private TagEntity makeNewTagEntity(PostEntity post,String keyword){
//        return TagEntity.builder().postEntity(post).keyword(keyword).build();
//    }

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

    @Transactional
    public void updateComment(String content, Long commentIdx) {
        commentRepository.findById(commentIdx).ifPresent(commentEntity -> {
            commentEntity.setContent(content);
            commentRepository.save(commentEntity);
        });
    }

//    @Transactional
//    public Map<Integer, List<PostDto>> landingPage() {
//        List<PostEntity> postList = postRepository.landingPage();
//        Map<Integer, List<PostDto>> postMap = new HashMap<>();
//        int pageIndex = 0;
//        List<PostDto> currentPage = new ArrayList<>();
//
//        for (PostEntity postEntity : postList) {
//            if (currentPage.size() == 10) {
//                postMap.put(pageIndex, currentPage);
//                pageIndex++;
//                currentPage = new ArrayList<>();
//            }
//            currentPage.add(entityToDto(postEntity));
//        }
//
//        if (!currentPage.isEmpty()){
//            postMap.put(++pageIndex, currentPage);
//        }
//
//        return postMap;
//    }
//
//
    @Transactional
    public PostDto entityToDto(PostEntity postEntity) {
        var postImgList = postImgRepository.findPathByPostIdx(postEntity.getIdx());
        var postTagList = tagRepository.findKeywordByPostIdx(postEntity.getIdx());
        int postLikeCount = postLikeRepository.countPostLikeByPostIdx(postEntity.getIdx());

        return PostDto.builder().userIdx(postEntity.getUserEntity().getIdx())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .likeCount(postLikeCount)
                .postImgList(postImgList)
                .latitude(postEntity.getLatitude())
                .longitude(postEntity.getLongitude())
                .postTagList(postTagList)
                .build();
    }

    public List<PostDto> getPage(Pageable pageable) {
        List<PostEntity> postList = postRepository.findAll(pageable).getContent();
        return postList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
