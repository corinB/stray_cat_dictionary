package com.example.butler.repository;

import com.example.butler.entity.PetCatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetCatRepository extends JpaRepository<PetCatEntity, Long> {
    String query1 = "select c from  PetCatEntity c where c.userEntity.nick = :nick";
    @Query(value = query1)
    public List<PetCatEntity> findByNick(String nick);

    String nativeQuery2 = "select u.email, uc.name, uc.des " +
            "from user_t u " +
            "join pet_cat_t uc on u.idx = uc.user_idx " +
            "where u.nick = :nick";
    @Query(value = nativeQuery2, nativeQuery = true)
    public List<Object[]>findMyCatDes(String nick);

    String nativeQuery = "select * form user_cat where user_idx = :userIdx";
    @Query(value = nativeQuery, nativeQuery = true)
    public List<PetCatEntity> findByUserIdx(Long userIdx);
}
