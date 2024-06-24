package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PostImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImgRepository extends JpaRepository<PostImgEntity, Long> {
}
