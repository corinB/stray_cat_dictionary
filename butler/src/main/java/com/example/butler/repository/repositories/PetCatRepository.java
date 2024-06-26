package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PetCatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetCatRepository extends JpaRepository<PetCatEntity, Long> {

}
