package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PetCatImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCatImgRepository extends JpaRepository<PetCatImgEntity, Long> {

}
