package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PostEntity;
import com.example.butler.entity.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    String findPostByKeywordQuery = "select t.postEntity from TagEntity t where t.keyword = :keyword";
    @Query(value = findPostByKeywordQuery)
    public List<PostEntity> findPostByKeyword(String keyword);

    String findKeywordByPostIdxQuery = "select t.keyword from TagEntity t where t.postEntity.idx = :idx";
    @Query(value = findKeywordByPostIdxQuery)
    public List<String> findKeywordByPostIdx(Long idx);
}
