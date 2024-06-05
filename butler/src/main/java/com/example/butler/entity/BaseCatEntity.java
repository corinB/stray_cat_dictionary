package com.example.butler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class BaseCatEntity implements IEntityAdapter<LocalDateTime> {
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
