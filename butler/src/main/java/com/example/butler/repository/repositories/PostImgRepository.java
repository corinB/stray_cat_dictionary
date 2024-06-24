package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PostImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImgRepository extends JpaRepository<PostImgEntity, Long> {
    String findPathByPostIdxQuery = "select pi.path from PostImgEntity pi where pi.postEntity.idx = :idx";
    @Query(value = findPathByPostIdxQuery)
    public List<String> findPathByPostIdx(Long idx);
}
