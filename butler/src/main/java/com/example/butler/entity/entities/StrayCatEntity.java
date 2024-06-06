package com.example.butler.entity.entities;

import com.example.butler.entity.util.DefaultListener;
import com.example.butler.entity.util.base.BaseCatEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@EntityListeners(value = DefaultListener.class)
@Table(name = "stray_cat_t")
public class StrayCatEntity extends BaseCatEntity {
}
