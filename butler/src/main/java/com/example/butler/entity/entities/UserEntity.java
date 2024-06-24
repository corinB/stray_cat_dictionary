package com.example.butler.entity.entities;

import com.example.butler.entity.util.base.DefaultBaseEntity;
import com.example.butler.entity.util.DefaultListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(value = DefaultListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_t")
public class UserEntity extends DefaultBaseEntity {

    @Column(length = 50, nullable = false)
    private String nick; //닉네임

    @Column(length = 50, nullable = false, unique = true)
    private String email; //이메일

    // PetCatEntity FK 설정
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Builder.Default
    private List<PetCatEntity> userCatEntityList = new ArrayList<>();

    //반려묘 주가
    public void addPetCat(PetCatEntity petCatEntity) {
        userCatEntityList.add(petCatEntity);
    }


    // StrayCatEntity FK 설정
    @OneToMany(mappedBy = "userEntity")
    @ToString.Exclude
    @Builder.Default
    private List<StrayCatEntity> strayCatList = new ArrayList<>();

    //길고양이 주가
    public void addStrayCat(StrayCatEntity strayCatEntity) {
        strayCatList.add(strayCatEntity);
    }


    // UserImgEntity FK 설정
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<UserImgEntity> userImgEntities = new ArrayList<>();

    //이미지 주가
    public void addUserImg(UserImgEntity userImgEntity) {
        userImgEntities.add(userImgEntity);
    }


    // PostEntity FK 설정
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<PostEntity> postEntities = new ArrayList<>();


    // CommentEntity FK 설정
    @OneToMany(mappedBy = "userEntity")
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

    //PostLikeEntity FK 설정
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntities = new ArrayList<>();

    //게시물 LIKE 추가
    public void addPostLike(PostLikeEntity postLikeEntity) {
        postLikeEntities.add(postLikeEntity);
    }

}
