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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    // PostImgEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<PostImgEntity> postImgEntities = new ArrayList<>();


    // CommentEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

    // PostLikeEntity FK 설정
    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntityList = new ArrayList<>();
}
