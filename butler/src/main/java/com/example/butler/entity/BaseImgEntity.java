package com.example.butler.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class BaseImgEntity implements IEntityAdapter<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idx;

    protected String path;
    protected LocalDateTime crateAt;
    protected LocalDateTime updateAt;
}
