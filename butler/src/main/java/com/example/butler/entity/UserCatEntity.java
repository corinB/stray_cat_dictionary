package com.example.butler.entity;


import com.example.butler.entity.lisen.DefaultListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_cat_t")
public class UserCatEntity implements IEntityAdapter<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    private String name;
    private String des;


    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime crateAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

}
