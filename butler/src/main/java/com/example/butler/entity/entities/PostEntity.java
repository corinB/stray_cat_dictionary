package com.example.butler.entity.entities;

import com.example.butler.entity.util.DefaultListener;
import com.example.butler.entity.util.base.DefaultBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(value = DefaultListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_t")
public class PostEntity extends DefaultBaseEntity {

    @Column(length = 50, nullable = false)
    private String title;//제목
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //내용
    private String latitude;
    private String longitude;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    // PostImgEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @Builder.Default
    private List<PostImgEntity> postImgEntities = new ArrayList<>();

    // 이미지 추가
    public void addImg (PostImgEntity postImgEntity){
        postImgEntities.add(postImgEntity);
    }

    //이미지 삭제
    public void removeImgAll (){
        postImgEntities.clear();
    }


    // CommentEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

    // 댓글 추가
    public void addComment(CommentEntity commentEntity){
        commentEntities.add(commentEntity);
    }

    // PostLikeEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntityList = new ArrayList<>();

    // 좋아요 추가
    public void addLike(PostLikeEntity postLikeEntity){
        postLikeEntityList.add(postLikeEntity);
    }

    @OneToMany(mappedBy = "postEntity", orphanRemoval = true, cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    @Builder.Default
    private List<TagEntity> tagEntities = new ArrayList<>();
    // tag 추가
    public void addTag(TagEntity tagEntity){
        tagEntities.add(tagEntity);
    }

    public void removeTagAll (){
        tagEntities.clear();
    }

}
