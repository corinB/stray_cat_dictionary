package com.example.butler.entity.util.base;

import com.example.butler.entity.util.IEntityAdapter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
public abstract class DefaultBaseEntity implements IEntityAdapter<LocalDateTime> {
    // 엔티티 베이스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idx;
    protected LocalDateTime crateAt;
    protected LocalDateTime updateAt;
}
