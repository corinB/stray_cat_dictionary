package com.example.butler.entity.util.base;

import com.example.butler.entity.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter @Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class BaseCatEntity extends DefaultBaseEntity {
    private String name;
    private String des;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

}
