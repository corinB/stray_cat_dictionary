package com.example.butler.entity;

import com.example.butler.entity.lisen.DefaultListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_t")
public class PostEntity implements IEntityAdapter<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(length = 50, nullable = false)
    private String title;//제목
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //내용

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime crateAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

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

}
