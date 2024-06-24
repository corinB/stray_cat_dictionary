package com.example.butler.entity.util.base;
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
public abstract class BaseImgEntity extends DefaultBaseEntity {
    protected String path;
}
