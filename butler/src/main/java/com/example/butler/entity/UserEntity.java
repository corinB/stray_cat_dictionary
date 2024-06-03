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
@Table(name = "user_t")
public class UserEntity implements IEntityAdapter<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(length = 50, nullable = false)
    private String nick;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime crateAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<UserCatEntity> userCatEntityList = new ArrayList<>();
}
