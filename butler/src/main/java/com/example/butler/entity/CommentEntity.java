package com.example.butler.entity;

import com.example.butler.entity.lisen.DefaultListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_t")
public class CommentEntity implements IEntityAdapter<LocalDateTime>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

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


    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime crateAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
