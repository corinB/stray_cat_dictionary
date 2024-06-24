package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.CommentLikeEntity;
import com.example.butler.entity.entities.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long>{
}

