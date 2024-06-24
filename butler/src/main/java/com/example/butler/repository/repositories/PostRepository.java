package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PostEntity;
import com.example.butler.entity.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{

    //유저의 닉네임으로 포스트를 찾는 쿼리
    String findPostByUserNickQuery = "select p.idx from PostEntity p where p.userEntity.nick = :nick";
    @Query(value = findPostByUserNickQuery)
    public List<Long> findPostByNick(String nick);

    // 포스트 해당 이미지 찾기
    String findPostImgPathListByIdxQuery = "select pi.path from PostImgEntity pi where pi.postEntity.idx = :idx";
    @Query(value = findPostImgPathListByIdxQuery)
    public List<String> findPostImgPathListByIdx(Long idx);

    String findPostLikeListByIdxQuery = "select pl.idx from PostLikeEntity pl where pl.postEntity.idx = :idx";
    @Query(value = findPostLikeListByIdxQuery)
    public List<Long> findPostLikeListByIdx(Long idx);

    String getEntityByIdx = "select p from PostEntity p where p.idx = :idx";
    @Query(value = getEntityByIdx)
    public PostEntity getEntityByIdx(Long idx);

    String deletePostImgAllQuery = "delete from PostImgEntity pi where pi.postEntity.idx = :idx";
    @Modifying
    @Query(value = deletePostImgAllQuery)
    public void deletePostImgAllQuery(Long idx);

    String searchByTitleQuery = "select p from PostEntity p where p.title like %:title%";
    @Query(value = searchByTitleQuery)
    public List<PostEntity> searchByTitle(String title);

    String landingPageQuery = "select p from PostEntity p order by p.updateAt desc";
    @Query(value = landingPageQuery)
    public List<PostEntity> landingPage();

//    public List<PostEntity> getPage(Pageable pageable);
    Page<PostEntity> findAll(Pageable pageable);

}
