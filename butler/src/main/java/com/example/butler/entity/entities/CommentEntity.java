package com.example.butler.entity.entities;

import com.example.butler.entity.util.base.DefaultBaseEntity;
import com.example.butler.entity.util.DefaultListener;
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
@Table(name = "comment_t")
public class CommentEntity extends DefaultBaseEntity {

    @Column(nullable = false)
    private String content; //댓글 내용

    // UserEntity FK 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    // PostEntity FK 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;


    // CommentLikeEntity FK 설정
    @OneToMany(mappedBy = "commentEntity", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Builder.Default
    private List<CommentLikeEntity> commentLikeEntityList = new ArrayList<>();

    // 좋아요 추가
    public void addCommentLike(CommentLikeEntity commentLikeEntity) {
        commentLikeEntityList.add(commentLikeEntity);
    }
}
