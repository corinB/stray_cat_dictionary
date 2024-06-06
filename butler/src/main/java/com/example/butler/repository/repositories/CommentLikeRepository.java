package com.example.butler.repository.repositories;
import com.example.butler.entity.entities.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity,Long>{

    String findMyLikeContent = "SELECT cl.commentEntity.idx FROM CommentLikeEntity cl WHERE cl.userEntity.idx = :userIdx";


    @Query(findMyLikeContent)
    public List<Long> findMyLikeContent(String userIdx);


}
