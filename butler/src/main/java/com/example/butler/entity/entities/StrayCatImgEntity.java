package com.example.butler.entity.entities;

import com.example.butler.entity.entities.PetCatEntity;
import com.example.butler.entity.util.base.BaseImgEntity;
import com.example.butler.entity.util.DefaultListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@SuperBuilder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stray_cat_img_t")
public class StrayCatImgEntity extends BaseImgEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_idx")
    private PetCatEntity petCatEntity;
}
