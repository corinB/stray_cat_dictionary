package com.example.butler.entity.entities;


import com.example.butler.entity.entities.PostEntity;
import com.example.butler.entity.util.DefaultListener;
import com.example.butler.entity.util.base.BaseLikeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(value = DefaultListener.class)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "post_like")
public class PostLikeEntity extends BaseLikeEntity {
    // PostEntity FK 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;
}
