package com.example.butler.repository.repositories;

import com.example.butler.entity.entities.PetCatEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PetCatRepositoryTest {
    @Autowired
    PetCatRepository petCatRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void catSave7(){

        for(long i = 1; i < 8; i++){
            long finalI = i;
            userRepository.findById(i).ifPresent(userEntity -> {
                PetCatEntity petCatEntity = PetCatEntity.builder()
                        .name("cat name" + finalI)
                        .des("cat des" + finalI)
                        .userEntity(userEntity)
                        .build();
                petCatRepository.save(petCatEntity);
            });
        }

    }



}