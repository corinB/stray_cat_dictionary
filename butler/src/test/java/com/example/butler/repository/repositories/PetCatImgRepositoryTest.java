package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PetCatImgEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetCatImgRepositoryTest {
    @Autowired
    PetCatImgRepository petCatImgRepository;
    @Autowired
    PetCatRepository petCatRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save10Img(){
        for (long i = 2; i < 9 ; i++) {
            long finalI = i;
            petCatRepository.findById(i).ifPresent(petCatEntity -> {
                for (int j = 0; j < 10; j++) {
                    petCatImgRepository.save(PetCatImgEntity.builder()
                            .path( "cat" + finalI + "_img" + j)
                            .petCatEntity(petCatEntity)
                            .build());
                }
            });
        }
    }


}