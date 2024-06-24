package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.UserImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImgRepository extends JpaRepository<UserImgEntity, Long> {
}
