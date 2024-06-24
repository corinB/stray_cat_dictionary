package com.example.butler.entity.entities;

import com.example.butler.entity.util.DefaultListener;
import com.example.butler.entity.util.base.DefaultBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(value = DefaultListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag_t")
public class TagEntity extends DefaultBaseEntity {
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;
}
