package com.example.butler.entity.entities;

import com.example.butler.entity.util.DefaultListener;
import com.example.butler.entity.util.base.BaseCatEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = DefaultListener.class)
@Table(name = "pet_cat_t")
public class PetCatEntity extends BaseCatEntity {

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "petCatEntity", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<PetCatImgEntity> petCatImgEntityList = new ArrayList<>();

    public void addPetCatImg(PetCatImgEntity petCatImgEntity) {
        petCatImgEntityList.add(petCatImgEntity);
    }
}
