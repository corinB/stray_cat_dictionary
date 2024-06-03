package com.example.butler.repository;

import com.example.butler.entity.UserCatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCatRepository extends JpaRepository<UserCatEntity, Long> {

    String query1 = "select c from  UserCatEntity c where c.userEntity.nick = :nick";


    @Query(value = query1)
    public List<UserCatEntity> findByNick(String nick);
}
