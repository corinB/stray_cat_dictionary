package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    String findMyProfileImg = "select ui.path from UserImgEntity ui where ui.userEntity.idx = :idx";
    @Query(value = findMyProfileImg)
    public List<String> findMyProfileImg(Long idx);

    String findUserProfileImg = "select ui.path from UserImgEntity ui where ui.userEntity.nick = :nick";
    @Query(value = findUserProfileImg)
    public List<String> findUserProfileImg(String nick);

    String findMyLikePostList = "select p.idx from PostEntity p where p.userEntity.idx = :idx";
    @Query(value = findMyLikePostList)
    public List<Long> findMyLikePostList(Long idx);

    String getEntityByIdx = "select u  from UserEntity u where u.idx = :idx";
    @Query(value = getEntityByIdx)
    public UserEntity getEntityByIdx(Long idx);
}
